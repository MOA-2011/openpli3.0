DESCRIPTION = "iqon and technomate splash image"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

inherit deploy

PR = "r0"

S = "${WORKDIR}"

SRC_URI = " \
		   ${@base_contains("BRAND_NAME", "4D", "file://splash.bmp", "",d)} \
		   ${@base_contains("BRAND_NAME", "IQON", "file://splash.bmp file://factory.bmp", "",d)} \
		   ${@base_contains("BRAND_NAME", "EDISION", "file://optimuss.splash.bmp file://optimuss.factory.bmp", "",d)} \
		   ${@base_contains("BRAND_NAME", "MEDIABOX", "file://mediabox.splash.bmp file://factory.bmp", "",d)} \
		   "

do_deploy() {
	if [ "${BRAND_NAME}" = "4D" ];then
		install -m 0644 ${WORKDIR}/splash.bmp ${DEPLOYDIR}/${MACHINE}.splash.bmp
		install -m 0644 ${WORKDIR}/splash.bmp ${DEPLOYDIR}/
		install -m 0644 ${WORKDIR}/splash.bmp ${DEPLOYDIR}/factory.bmp
	elif [ "${BRAND_NAME}" = "IQON" ];then
		install -m 0644 ${WORKDIR}/splash.bmp ${DEPLOYDIR}/${MACHINE}.splash.bmp
		install -m 0644 ${WORKDIR}/splash.bmp ${DEPLOYDIR}/
		install -m 0644 ${WORKDIR}/factory.bmp ${DEPLOYDIR}/
	elif [ "${BRAND_NAME}" = "EDISION" ];then
		install -m 0644 ${WORKDIR}/optimuss.splash.bmp ${DEPLOYDIR}/${MACHINE}.splash.bmp
		install -m 0644 ${WORKDIR}/optimuss.splash.bmp ${DEPLOYDIR}/
		install -m 0644 ${WORKDIR}/optimuss.factory.bmp ${DEPLOYDIR}/
	elif [ "${BRAND_NAME}" = "MEDIABOX" ];then
		install -m 0644 ${WORKDIR}/mediabox.splash.bmp ${DEPLOYDIR}/${MACHINE}.splash.bmp
		install -m 0644 ${WORKDIR}/mediabox.splash.bmp ${DEPLOYDIR}/${MACHINE}.bmp
		install -m 0644 ${WORKDIR}/factory.bmp ${DEPLOYDIR}/
	fi
}

addtask deploy before do_build after do_install
