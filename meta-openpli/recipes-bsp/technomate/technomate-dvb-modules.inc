DESCRIPTION = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

KV = "3.9.7"
KV_EXTRA = ""
PV = "${KV}+${SRCDATE}"

SRCDATE = "20140212"

SRC_URI = "http://en3.homeftp.net/release/images/oedrivers//bcmlinuxdvb_${CHIP}-${KV}-${SRCDATE}.tar.gz"

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
		done
		install -d ${D}/${sysconfdir}/modules-load.d
		for i in `ls ${D}/lib/modules/${KV}/extra | grep \\.ko | sed -e 's/.ko//g'`; do
		    echo $i _hwtype=\$hwtypenum >> ${D}/${sysconfdir}/modules-load.d/_${MACHINE}.conf
		done
}

SRC_URI[md5sum] = "3851055bc3addf3acd3260063b392c7d"
SRC_URI[sha256sum] = "18b16b2d286c285144396a523a75fc02cd1432ee3e1aad7595e00d227711c2f7"
