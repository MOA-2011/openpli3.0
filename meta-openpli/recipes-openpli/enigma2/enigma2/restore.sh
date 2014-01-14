#!/bin/sh
#
# Do not change this script. if you change something, we can not support restore.
#

exec 1>/tmp/restore.log
exec 2>&1
set -x

BACKUP_MTD_NUM=5
MTD_DEVICE=/dev/mtdblock$BACKUP_MTD_NUM
MTD_MOUNTPOINT=/tmp/.mtd${BACKUP_MTD_NUM}_disk
MTD_BACKUP_DIR=$MTD_MOUNTPOINT/backups

mount_mtdblock()
{
	DEVICE=$MTD_DEVICE
	MOUNTPOINT=$MTD_MOUNTPOINT

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
		echo == failed to mount mtd, exit...
		return 1
	fi

	return 0
}

remove_backup_dir()
{
	BACKUP_DIR=$MTD_BACKUP_DIR
	# make backup directory
	if [ -e $BACKUP_DIR ]; then
		echo clear backup directory...
		rm -rf $BACKUP_DIR
	fi
}


echo start restore
mount_mtdblock

if [ -e $MTD_BACKUP_DIR/settings.tar ]; then
	echo ++ restore settings file
	tar xf $MTD_BACKUP_DIR/settings.tar -C /
fi

if [ -e $MTD_BACKUP_DIR/channel_lists.tar ]; then
	echo ++ restore channel lists
	tar xf $MTD_BACKUP_DIR/channel_lists.tar -C /
	mv /etc/def_inst /etc/.def_inst
fi

if [ -e $MTD_BACKUP_DIR/configurations.tar ]; then
	echo ++ restore configurations
	tar xf $MTD_BACKUP_DIR/configurations.tar -C /
fi

remove_backup_dir
umount $MTD_MOUNTPOINT
rmdir $MTD_MOUNTPOINT





