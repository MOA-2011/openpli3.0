DESCRIPTION = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

KV = "3.9.7"
KV_EXTRA = ""
PV = "${KV}+${SRCDATE}"

SRCDATE = "20141010"

SRC_URI = "http://en3homeftp.net/release/images/oedrivers/bcmlinuxdvb_${CHIP}-${KV}-${SRCDATE}.tar.gz"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

inherit module

do_compile() {
}

FILES_${PN} += "${sysconfdir}/modules-load.d/_technomate.conf"

do_configure_prepend() {
		if [ -d ${DEPLOY_DIR_IMAGE} ]; then
		   mkdir -p ${DEPLOY_DIR_IMAGE}
		fi
}
do_install() {
		install -d ${D}/lib/modules/${KV}/extra

		for f in ${S}/lib/modules/${KV}/extra/*.ko; do
			install -m 0644 $f ${D}/lib/modules/${KV}/extra;
			cp $f ${DEPLOY_DIR_IMAGE}
		done

		install -d ${D}/${sysconfdir}/modules-load.d
		for i in `ls ${D}/lib/modules/${KV}/extra | grep \\.ko | sed -e 's/.ko//g'`; do
		    echo $i _hwtype=\$hwtypenum >> ${D}/${sysconfdir}/modules-load.d/_${MACHINE}.conf
		done
}

SRC_URI[md5sum] = "1de92c6a395572b30e3b17ef65589a32"
SRC_URI[sha256sum] = "8784a2e0bea0ff53f7a02feb748619285d6297d6351cd398447c3fb1be99a59d"
