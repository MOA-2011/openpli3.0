#!/bin/sh

FRONT_KEY_MENU=1
FRONT_KEY_OK=2
FRONT_KEY_EXIT=3
FRONT_KEY_CHDOWN=4
FRONT_KEY_CHUP=5
FRONT_KEY_VOLDOWN=6
FRONT_KEY_VOLUP=7

frontled_message()
{
	echo $1
	echo $1 > /dev/dbox/lcd0
}

kill_process()
{
	DAEMONS="dropbear busybox-cron dbus-1 syslog"
	for daemon in $DAEMONS
	do
		if [ -e /etc/init.d/$daemon ]; then
			/etc/init.d/$daemon stop 
		fi
	done

	SOFTCAMS="CCcam mgcam scam oscam newcs hypercam camd"
	for cam in $SOFTCAMS
	do 
		if [ "`ps | grep -i $cam`" != "" ]; then
			kill `ps | grep -i $cam | awk '{print $1}'` 2> /dev/null
		fi
	done
}

BACKUP_MTD_NUM=5
MTD_DEVICE=/dev/mtdblock$BACKUP_MTD_NUM
MTD_MOUNTPOINT=/tmp/.mtd${BACKUP_MTD_NUM}_disk
MTD_BACKUP_DIR=$MTD_MOUNTPOINT/backups

mount_mtdblock()
{
	DEVICE=$MTD_DEVICE
	MOUNTPOINT=$MTD_MOUNTPOINT
	BACKUP_DIR=$MTD_BACKUP_DIR

	# if mounted mtdblock, unmount it
	mount_check=`mount | grep $DEVICE | wc -l`
	if [ $mount_check -eq 1 ]; then
		echo == mounted on some directory, umount it...
		mounted_point = `mount | grep $DEVICE | awk '{print $3}'`
		umount $mounted_point
		if [ $? -ne 0 ]; then
			echo == failed to umount, exit...
			return 1
		fi
	fi
	
	# make mtdblock MOUNTPOINT
	if [ -e $MOUNTPOINT ]; then
		echo == before backup, remove  directory used before for mounting...
		rm -rf $MOUNTPOINT
	fi
	mkdir $MOUNTPOINT
		
	# mount mtdblock
	mount -t jffs2 $DEVICE $MOUNTPOINT
	if [ $? -ne 0 ]; then
		echo == failed to mount mtd, erase with jffs2...
		flash_eraseall -j /dev/mtd$BACKUP_MTD_NUM
		mount -t jffs2 $DEVICE $MOUNTPOINT
		if [ $? -ne 0 ]; then
			echo == faild to mount, exit...
			return 1
		fi
	fi

	# make backup directory
	if [ -e $BACKUP_DIR ]; then
		echo == before backup, remove backup directory used before...
		rm -rf $BACKUP_DIR
	fi
	mkdir $BACKUP_DIR

	return 0
}

unmount_mtdblock()
{
	umount $MTD_MOUNTPOINT
	if [ $? -ne 0 ]; then
		return 1
	else
		return 0
	fi
}

backup_settings()
{
	echo == backup settings
	BACKUP_DIR=$MTD_BACKUP_DIR
	SETTINGS_TAR=$BACKUP_DIR/settings.tar

	tar cf $SETTINGS_TAR /etc/enigma2/settings
}

backup_configurations()
{
	echo == backup configurations
	BACKUP_DIR=$MTD_BACKUP_DIR
	CONFIG_TAR=$BACKUP_DIR/configurations.tar
	CONFIG_FILES=""

	for file in /var/etc/CCcam.cfg /var/keys/newcamd.list /etc/passwd /var/keys/camd3.config /var/tuxbox/config/newcs.xml
	do
		if [ -e $file ]; then
			CONFIG_FILES="$CONFIG_FILES $file"
		fi
	done

	tar cf $CONFIG_TAR $CONFIG_FILES
}

backup_channel_lists()
{
	echo == backup channel lists
	BACKUP_DIR=$MTD_BACKUP_DIR
	CHLISTS_TAR=$BACKUP_DIR/channel_lists.tar
	CHLISTS_FILES=""

	for file in /etc/enigma2/lamedb /etc/enigma2/whitelist /etc/enigma2/blacklist /etc/enigma2/satellites.xml /etc/enigma2/*.tv /etc/enigma2/*.radio
	do
		if [ -e $file ]; then
			CHLISTS_FILES="$CHLISTS_FILES $file"
		fi
	done

	tar cf $CHLISTS_TAR $CHLISTS_FILES
}

usbupdate_process()
{
	frontled_message "Usb update"

	kill_process

	HWMODEL=`cat /proc/stb/info/hwmodel`
	if [ "$HWMODEL" == "twin" ]; then
		HWMODEL=tmtwinoe
	elif [ "$HWMODEL" == "tm2t" ]; then
		HWMODEL=tm2toe
	fi

	IMAGE_ROOTFS=update/$HWMODEL/rootfs.bin
	IMAGE_OE_ROOTFS=update/$HWMODEL/oe_rootfs.bin
	IMAGE_BCMDRV=update/$HWMODEL/bcmdrv.bin

	REBOOT=0

	TMP_MNT_POINT=/tmp/dlatlelfprxhfl
	MNT_POINT=$TMP_MOUNT_POINT

	while [ 1 ]; do
		for index in a b c; do
			MOUNTED=0
			DEV=/dev/sd${index}
			DEV1=${DEV}1
			if [ -e $DEV ]; then
				if [ -e $DEV1 ]; then
					if [ "`mount | grep $DEV1`" != "" ]; then
						MOUNTED=1
						MNT_POINT=`mount | grep $DEV1 | awk '{print $3}'`
					else
						mkdir $MNT_POINT
						mount $DEV1 $MNT_POINT
					fi
				else
					if [ "`mount | grep $DEV`" != "" ]; then
						MOUNTED=1
						MNT_POINT=`mount | grep $DEV | awk '{print $3}'`
					else
						mkdir $MNT_POINT
						mount $DEV $MNT_POINT
					fi
				fi

				# rootfs update
				if [ -e $MNT_POINT/$IMAGE_ROOTFS ]; then
					frontled_message "Updating rootfs"
					/usr/bin/update_pr	$MNT_POINT/$IMAGE_ROOTFS
					if [ $? -eq 0 ]; then
						REBOOT=1
					fi
				fi

				# bcmdrv(recovery fs) update
				if [ -e $MNT_POINT/$IMAGE_BCMDRV ]; then
					frontled_message "Updating bcmdrv"
					/usr/bin/update_pr	$MNT_POINT/$IMAGE_BCMDRV
					if [ $? -eq 0 ]; then
						REBOOT=1
					fi
				fi

				# oe rootfs update
				if [ -e $MNT_POINT/$IMAGE_OE_ROOTFS ]; then
					frontled_message "Updating rootfs"

					# backup user data
					mount_mtdblock
					backup_settings
					backup_configurations
					backup_channel_lists
					unmount_mtdblock

					cp /sbin/reboot /tmp
					/usr/bin/update_pr	$MNT_POINT/$IMAGE_OE_ROOTFS
					if [ $? -eq 0 ]; then
						REBOOT=1
					fi
				fi
				
				if [ $MOUNTED -eq 0 ]; then
					umount $MNT_POINT
					rm -rf $MNT_POINT
				fi

				if [ $REBOOT -eq 1 ]; then
					frontled_message "Reboot"
					/tmp/reboot
					exit 0
				fi
			fi
		done

		frontled_message "Usb fail"
		sleep 2
		frontled_message "Checking usb"
		sleep 1
	done
}

internetupdate_process()
{
		frontled_message "Internet update"
		kill_process
		sleep 3 #for wait to stop emul
		/usr/bin/getlastimage	
		if [ $? -eq "0" ]
		then
			cp /sbin/reboot /tmp/reboot
			update_pr /tmp/update.bin
			/tmp/reboot
		fi
		frontled_message "Reboot"
		exit 0
}

generate_default_ipkg_list()
{
	if [ ! -e /usr/lib/ipkg/ipkg_list_factory ]; then
		ipkg list-installed | awk '{print $1}' | sort > /usr/lib/ipkg/ipkg_list_factory

		init_softcam_link
	fi
}

init_softcam_link()
{
	if [ -e /etc/init.d/softcam ]; then
		/etc/init.d/softcam stop
		rm -f /etc/init.d/softcam
	fi

	if [ -e /etc/init.d/cardserver ]; then
		/etc/init.d/cardserver stop
		rm -f /etc/init.d/cardserver
	fi

	if [ -e /etc/init.d/softcam.CCcam221 ]; then
		ln -s /etc/init.d/softcam.CCcam221 /etc/init.d/softcam
		ln -s /etc/init.d/cardserver.None /etc/init.d/cardserver
		sleep 2
		/etc/init.d/softcam start
	fi
}

foldname=/etc/opkg/
host=/etc/hostname

generate_distro_bymodel()
{
HWMODEL=`cat /proc/stb/info/hwmodel`

for LINE in `ls $foldname`
	do
		filename=`basename $LINE .txt`

		if [ "$HWMODEL" == "tm2toe" ];then
			if [ "$LINE" == "tmtwinoesr-feed.conf" ];then
				mv $foldname/tmtwinoesr-feed.conf $foldname/$HWMODEL-feed.conf
				echo "src/gz openpli-tmtwinoesr http://en2.ath.cx/Openembedded/$HWMODEL" > $foldname/$HWMODEL-feed.conf
				echo "$HWMODEL" > $host
				`sed -i 's/tmtwinoesr/tm2toe/g' $foldname/arch.conf`
			fi
		elif [ "$HWMODEL" == "tmsingle" ];then
			if [ "$LINE" == "tmtwinoesr-feed.conf" ];then
				mv $foldname/tmtwinoesr-feed.conf $foldname/$HWMODEL-feed.conf
				echo "src/gz openpli-tmtwinoesr http://en2.ath.cx/Openembedded/$HWMODEL" > $foldname/$HWMODEL-feed.conf
				echo "$HWMODEL" > $host
				`sed -i 's/tmtwinoesr/tmsingle/g' $foldname/arch.conf`
			fi
		fi
done
}

#@ ipkg list update by model
generate_distro_bymodel

generate_default_ipkg_list
# [iq
#keycheck_process
# iq]

frontled_message "`cat /proc/stb/info/hwmodel`"




