DESCRIPTION = "opera-hbbtv-browser"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "CLOSED"

DEPENS = "tslib mpfr gmp "
RDEPENS = "tslib-conf libts-1.0-0 libsysfs2 libgmp3 libmpfr1 "

SRC_DATE = "20121128_0"
SRC_URI = "http://ilove.hobby-site.com/pub/OpenPLi3/src/opera-hbbtv_${SRC_DATE}.tar.gz \
		   file://setting.ini \
		   "

PR = "r2_${SRC_DATE}"

S = "${WORKDIR}/opera-hbbtv"

do_install() {
	install -d ${D}/usr
	install -d ${D}/usr/local
	install -d ${D}/usr/local/hbb-browser

	mv ${S}/opera/lib/libopera.so ${S}/opera/lib/libopera.so.0
	cp -avR ${S}/opera/* ${D}/usr/local/hbb-browser/
	cp ${WORKDIR}/setting.ini ${D}/usr/local/hbb-browser/home/

	install -d ${D}/etc
	cp -avR ${S}/dfb/etc/* ${D}/etc/

	install -d ${D}/usr/bin
	cp -avR ${S}/dfb/usr/bin/* ${D}/usr/bin/

	install -d ${D}/usr/lib
	cp -avR ${S}/dfb/usr/lib/* ${D}/usr/lib/

	install -d ${D}/usr/share
	cp -avR ${S}/dfb/usr/share/* ${D}/usr/share/

	install -d ${D}/usr/lib/enigma2
	install -d ${D}/usr/lib/enigma2/python
	install -d ${D}/usr/lib/enigma2/python/Plugins
	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions
	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV
	cp -avR ${S}/plugin/* ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV
}

INHIBIT_PACKAGE_STRIP = "1"

FILES_${PN} = "/usr/lib /usr/local /usr/share /usr/bin /etc "
PACKAGES = "${PN}"
PROVIDES="${PACKAGES}"
PACKAGE_ARCH := "${MACHINE_ARCH}"

SRC_URI[md5sum] = "5a8bf37321bcb2c8b23b4538d3862e87"
SRC_URI[sha256sum] = "4e3a41188b81a871d7f0bf559d1cd0efc5f2faded9d37ca81c4e28e0675b5178"
