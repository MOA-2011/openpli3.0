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
		   file://splash1.bmp \
		   file://splash2.bmp \
		   file://splash3.bmp \
		   file://optimuss.splash.bmp \
		   file://optimuss.factory.bmp \
		   file://mediabox.splash.bmp \
			"

do_install() {
	if [ ! -d ${DEPLOY_DIR_IMAGE} ]; then
		mkdir -p ${DEPLOY_DIR_IMAGE}
	fi
	if [ "${BRAND_NAME}" = "Technomate" ];then
		install -m 0644 ${WORKDIR}/splash.bmp ${DEPLOY_DIR_IMAGE}/${MACHINE}.splash.bmp
		install -m 0644 ${WORKDIR}/splash.bmp ${DEPLOY_DIR_IMAGE}/
		install -m 0644 ${WORKDIR}/splash.bmp ${DEPLOY_DIR_IMAGE}/factory.bmp
        ${@base_contains("CHIP","7356","install -m 0644 ${WORKDIR}/splash1.bmp ${DEPLOY_DIR_IMAGE}", "", d)}
        ${@base_contains("CHIP","7356","install -m 0644 ${WORKDIR}/splash2.bmp ${DEPLOY_DIR_IMAGE}", "", d)}
        ${@base_contains("CHIP","7356","install -m 0644 ${WORKDIR}/splash3.bmp ${DEPLOY_DIR_IMAGE}", "", d)}
	elif [ "${BRAND_NAME}" = "Iqon" ];then
		install -m 0644 ${WORKDIR}/splash.bmp ${DEPLOY_DIR_IMAGE}/${MACHINE}.splash.bmp
		install -m 0644 ${WORKDIR}/splash.bmp ${DEPLOY_DIR_IMAGE}/
        ${@base_contains("CHIP","7356","install -m 0644 ${WORKDIR}/splash1.bmp ${DEPLOY_DIR_IMAGE}", "", d)}
        ${@base_contains("CHIP","7356","install -m 0644 ${WORKDIR}/splash2.bmp ${DEPLOY_DIR_IMAGE}", "", d)}
        ${@base_contains("CHIP","7356","install -m 0644 ${WORKDIR}/splash3.bmp ${DEPLOY_DIR_IMAGE}", "", d)}
		install -m 0644 ${WORKDIR}/factory.bmp ${DEPLOY_DIR_IMAGE}/
	elif [ "${BRAND_NAME}" = "Edition" ];then
		install -m 0644 ${WORKDIR}/optimuss.factory.bmp ${DEPLOY_DIR_IMAGE}/${MACHINE}.splash.bmp
		install -m 0644 ${WORKDIR}/optimuss.factory.bmp ${DEPLOY_DIR_IMAGE}/
		install -m 0644 ${WORKDIR}/optimuss.factory.bmp ${DEPLOY_DIR_IMAGE}/
        ${@base_contains("CHIP","7356","install -m 0644 ${WORKDIR}/splash1.bmp ${DEPLOY_DIR_IMAGE}", "", d)}
        ${@base_contains("CHIP","7356","install -m 0644 ${WORKDIR}/splash2.bmp ${DEPLOY_DIR_IMAGE}", "", d)}
        ${@base_contains("CHIP","7356","install -m 0644 ${WORKDIR}/splash3.bmp ${DEPLOY_DIR_IMAGE}", "", d)}
	elif [ "${BRAND_NAME}" = "Mediabox" ];then
		install -m 0644 ${WORKDIR}/mediabox.splash.bmp ${DEPLOY_DIR_IMAGE}/${MACHINE}.splash.bmp
		install -m 0644 ${WORKDIR}/mediabox.splash.bmp ${DEPLOY_DIR_IMAGE}/${MACHINE}.bmp
		install -m 0644 ${WORKDIR}/factory.bmp ${DEPLOY_DIR_IMAGE}/
	fi  
}
