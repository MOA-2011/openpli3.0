MACHINE_ESSENTIAL_EXTRA_RDEPENDS = " \
	technomate-dvb-modules-7356 \
	"

include conf/machine/include/autoload-filesystems.inc

MACHINE_EXTRA_RRECOMMENDS = " \
	vuplus-shutdown \
	gst-plugin-dvbmediasink \
	ntfs-3g \
	"

EXTRA_IMAGEDEPENDS += "\
	"

TARGET_ARCH = "mipsel"
DEFAULTTUNE = "mips32el"

GLIBC_EXTRA_OECONF = "--with-tls"
DVBMEDIASINK_CONFIG = "--with-wma --with-wmv --with-pcm --with-dts"

PREFERRED_VERSION_linux-${MACHINE} = "3.9.7"
PREFERRED_VERSION_linux-libc-headers = "3.9.7"

KV = "linux-kernel-3.9.7"

PREFERRED_PROVIDER_virtual/kernel = "linux-${MACHINE}"

PREFERRED_PROVIDER_virtual/blindscan-dvbc = "vuplus-blindscan-utils-${MACHINE}"
PREFERRED_PROVIDER_virtual/blindscan-dvbs = "vuplus-blindscan-utils-${MACHINE}"

IMAGE_FSTYPES ?= "ubi"

UBI_VOLNAME = "rootfs"
MKUBIFS_ARGS = "-m 2048 -e 126976 -c 4096 -F"
UBINIZE_ARGS = "-m 2048 -p 128KiB"
//MKUBIFS_ARGS = "-m 4096 -e 1040384 -c 4096 -F"
//UBINIZE_ARGS = "-m 4096 -p 1024KiB"

IMAGEDIR ?= "${MACHINE}"
//EXTRA_IMAGECMD_COMPAT = " --eraseblock=0x100000 -n -l --pagesize=1000 "
EXTRA_IMAGECMD_COMPAT = " --eraseblock=0x20000 -n -l --pagesize=0x800 "

IMAGE_CMD_ubi_prepend = " \
        mkfs.jffs2 --root=${IMAGE_ROOTFS}/boot --faketime \
        --disable-compressor=lzo --compression-mode=size \
        --output=${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.boot.jffs2 \
        ${EXTRA_IMAGECMD_COMPAT}; rm -rf ${IMAGE_ROOTFS}/boot/*; \
        "

IMAGE_CMD_ubi_append = " \
        mkdir -p ${DEPLOY_DIR_IMAGE}/update/${MACHINE}/cfe/; \
        cp ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ubi ${DEPLOY_DIR_IMAGE}/update/${MACHINE}/cfe/oe_rootfs.bin; \
        cp ${DEPLOY_DIR_IMAGE}/cfe-${MACHINE}.bin ${DEPLOY_DIR_IMAGE}/update/${MACHINE}/cfe/cfe.bin; \
		cp ${DEPLOY_DIR_IMAGE}/${MACHINE}.splash.bmp ${DEPLOY_DIR_IMAGE}/update/${MACHINE}/cfe/splash.bmp; \
        gzip -f -9c ${DEPLOY_DIR_IMAGE}/vmlinux-${MACHINE}.bin > ${DEPLOY_DIR_IMAGE}/update/${MACHINE}/cfe/oe_kernel.bin; \
        cd ${DEPLOY_DIR_IMAGE}; \
        zip ${IMAGE_NAME}-${KV}-${DATE}-usb.zip update/${MACHINE}/cfe/*; \
		rm -rf update; \
		rm -rf ${DEPLOY_DIR_IMAGE}/enigma2_2.7*; \
		cp ${DEPLOY_DIR_IPK}/${MACHINE}/enigma2_2.7* ${DEPLOY_DIR_IMAGE}; \
		gzip -f -c ${DEPLOY_DIR_IMAGE}/vmlinux-3.9.7*.bin > ${DEPLOY_DIR_IMAGE}/vmlinux-${MACHINE}.bin.gz; \
		cp ${DEPLOY_DIR_IMAGE}/vmlinux-${MACHINE}.bin.gz ${DEPLOY_DIR_IMAGE}/oe_kernel.bin; \
		tar cf ${DEPLOY_DIR_IMAGE}/test_${MACHINE}_${DATE} oe_kernel.bin enigma2_2.7* bcmlinuxdvb.ko; \
        "

TARGET_FPU = "hard"

MACHINE_FEATURES += "alsa usbhost wifi 3dtv switchoff osdposition hdmicec"

require conf/machine/include/tune-mips32.inc
