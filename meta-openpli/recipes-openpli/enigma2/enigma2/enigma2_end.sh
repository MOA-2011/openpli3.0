#!/bin/sh

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


factory_reset()
{
	echo == factory reset
	tar xf /etc/var.tar -C /
	/tmp/factory_reset.sh $1
	rm /tmp/*.ipk
	rm /tmp/factory_reset.sh
}

update()
{
	/usr/bin/showiframe /usr/share/wait.mvi

	kill_process
	sleep 6		# for waiting to stop emul
	cp /sbin/reboot /tmp/reboot
#	update_pr /tmp/update.img
	update_pr `cat /tmp/.update_file`; sleep 3; echo "Restart" > /dev/dbox/lcd0; /tmp/reboot
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

#mount_mtdblock		mtd for old internet update, for now not supporting jffs2 or ubi utils to mount as ubifs
echo == ret $1
case $1 in
	64)
		echo == full factory reset
		factory_reset all
		;;
	65)
		echo == factory reset
		factory_reset
		;;
	90)
		echo == update
		update
		;;
	91)
		echo == update, backup settings
		backup_settings
		update
		;;
	92)
		echo == update, backup configurations
		backup_configurations
		update
		;;
	93)
		echo == update, backup settings, configurations
		backup_settings
		backup_configurations
		update
		;;
	94)
		echo == update, backup channel lists
		backup_channel_lists
		update
		;;
	95)
		echo == update, backup settings, channel lists
		backup_settings
		backup_channel_lists
		update
		;;
	96)
		echo == update, backup configurations, channel lists
		backup_configurations
		backup_channel_lists
		update
		;;
	97)
		echo == update, backup settings, configurations, channel lists
		backup_settings
		backup_configurations
		backup_channel_lists
		update
		;;
	*)
		;;
esac
