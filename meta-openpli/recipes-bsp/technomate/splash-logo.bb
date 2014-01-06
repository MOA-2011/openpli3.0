DESCRIPTION = "TM bootlogo"
SECTION = "base"
PRIORITY = "required"
MAINTAINER = "4D"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LICENSE = "CLOSED"

PV = "1.0"
PR = "r0"

S = "${WORKDIR}"

SRC_URI = "file://factory.bmp \
		   file://splash.bmp \
		   file://optimuss.splash.bmp \
		   file://optimuss.factory.bmp \
		   file://mediabox.splash.bmp \
			"

do_install() {
	if [ "${BRAND_NAME}" = "Technomate" ];then
		install -m 0644 ${WORKDIR}/splash.bmp ${DEPLOY_DIR_IMAGE}/${MACHINE}.splash.bmp
		install -m 0644 ${WORKDIR}/splash.bmp ${DEPLOY_DIR_IMAGE}/
		install -m 0644 ${WORKDIR}/splash.bmp ${DEPLOY_DIR_IMAGE}/factory.bmp
	elif [ "${BRAND_NAME}" = "Iqon" ];then
		install -m 0644 ${WORKDIR}/splash.bmp ${DEPLOY_DIR_IMAGE}/${MACHINE}.splash.bmp
		install -m 0644 ${WORKDIR}/splash.bmp ${DEPLOY_DIR_IMAGE}/
		install -m 0644 ${WORKDIR}/factory.bmp ${DEPLOY_DIR_IMAGE}/
	elif [ "${BRAND_NAME}" = "Edition" ];then
		install -m 0644 ${WORKDIR}/optimuss.splash.bmp ${DEPLOY_DIR_IMAGE}/${MACHINE}.splash.bmp
		install -m 0644 ${WORKDIR}/optimuss.splash.bmp ${DEPLOY_DIR_IMAGE}/
		install -m 0644 ${WORKDIR}/optimuss.factory.bmp ${DEPLOY_DIR_IMAGE}/
	elif [ "${BRAND_NAME}" = "Mediabox" ];then
		install -m 0644 ${WORKDIR}/mediabox.splash.bmp ${DEPLOY_DIR_IMAGE}/${MACHINE}.splash.bmp
		install -m 0644 ${WORKDIR}/mediabox.splash.bmp ${DEPLOY_DIR_IMAGE}/${MACHINE}.bmp
		install -m 0644 ${WORKDIR}/factory.bmp ${DEPLOY_DIR_IMAGE}/
	fi  
}
