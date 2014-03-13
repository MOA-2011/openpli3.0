DESCRIPTION = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

KV = "3.9.7"
KV_EXTRA = ""
PV = "${KV}+${SRCDATE}"

SRCDATE = "20140313"

SRC_URI = "http://en3.homeftp.net/release/images/oedrivers/bcmlinuxdvb_${CHIP}-${KV}-${SRCDATE}.tar.gz"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

inherit module

do_compile() {
}

FILES_${PN} += "${sysconfdir}/modules-load.d/_technomate.conf"

do_install() {
		install -d ${D}/lib/modules/${KV}/extra
		for f in ${S}/lib/modules/${KV}/extra/*.ko; do
			install -m 0644 $f ${D}/lib/modules/${KV}/extra;
			cp $f ${DEPLOY_DIR}
		done
		install -d ${D}/${sysconfdir}/modules-load.d
		for i in `ls ${D}/lib/modules/${KV}/extra | grep \\.ko | sed -e 's/.ko//g'`; do
		    echo $i _hwtype=\$hwtypenum >> ${D}/${sysconfdir}/modules-load.d/_${MACHINE}.conf
		done
}

SRC_URI[md5sum] = "f63865c088b9e3aa45fe52525e57edc2"
SRC_URI[sha256sum] = "79c23f7bfdd07e4aaf3c9e44cede0d610c3416048c3d8fb013bd4bfbf7a75c6c"
