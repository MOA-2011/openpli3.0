LICENSE = "CLOSED"
DESCRIPTION = " the tmtwin Universal cccam config"

PN = "enigma2-plugin-softcams-cccam-config"
PV = "1.0"
PR = "r0"

SRC_URI = "http://en2.ath.cx/pub/OpenPLi3/src/cccam-config-${PV}.tar.gz"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

do_compile(){
}

do_install() {
        install -d ${D}/etc
        install -m 0644 ${S}/etc/CCcam.cfg ${D}/etc/CCcam.cfg
}

SRC_URI[md5sum] = "b9f02a1bcc43e161b3b9ec4a568db442"
SRC_URI[sha256sum] = "1995fdc45dddadc18a77f466a2fa39309dfc1ae41c8afd756f8120f7d05e08d4"

