LICENSE = "CLOSED"
DESCRIPTION = " the tmtwin Universal newcs config"

PN = "enigma2-plugin-softcams-newcs-config"
PV = "1.67"
PR = "r0"

SRC_URI = "http://en2.ath.cx/pub/OpenPLi3/src/newcs-config-${PV}.tar.gz"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

do_compile(){
}

do_install() {
	install -d ${D}/etc/tuxbox/config
	install -m 0644 ${S}/newcs-config/etc/tuxbox/config/newcs.xml ${D}/etc/tuxbox/config/newcs.xml
}

SRC_URI[md5sum] = "d3f9b9fb7463873e7bad239ee69b36af"
SRC_URI[sha256sum] = "0f3ceb53cdf342c6c81ba5e955d026260f816263143d282d616e3330cb988cc2"