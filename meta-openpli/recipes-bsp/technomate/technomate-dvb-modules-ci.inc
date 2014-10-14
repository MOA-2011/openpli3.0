DESCRIPTION = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

KV = "3.9.7"
KV_EXTRA = ""
PV = "${KV}+${SRCDATE}"

SRCDATE = "20141010"

SRC_URI = "http://en3homeftp.net/release/images/oedrivers/ci/bcmlinuxdvb_${CHIP}-${KV}-${SRCDATE}.tar.gz"

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
		if [ ! d ${DEPLOY_DIR_IMAGE} ]; then
			install -d ${DEPLOY_DIR_IMAGE}
		fi

		for f in ${S}/lib/modules/${KV}/extra/*.ko; do
			install -m 0644 $f ${D}/lib/modules/${KV}/extra;
			cp $f ${DEPLOY_DIR_IMAGE}
		done

		install -d ${D}/${sysconfdir}/modules-load.d
		for i in `ls ${D}/lib/modules/${KV}/extra | grep \\.ko | sed -e 's/.ko//g'`; do
		    echo $i _hwtype=\$hwtypenum >> ${D}/${sysconfdir}/modules-load.d/_${MACHINE}.conf
		done
}

SRC_URI[md5sum] = "bd7e97ecb35e2fe37cb2c2c7641f8153"
SRC_URI[sha256sum] = "d8fad1a09a1c7ef122d84e4431bf12c1e26976cfb8a0e06cb476c8f6e07041b9"
