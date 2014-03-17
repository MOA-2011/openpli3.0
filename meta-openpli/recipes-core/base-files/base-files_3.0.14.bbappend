PRINC = "3"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

do_install_append() {
	rm -rf ${D}/mnt
	rm -rf ${D}/hdd
	ln -sf media/hdd ${D}/hdd
	ln -sf media ${D}/mnt
	rm -rf ${D}/media/*
	rm -fr ${D}/tmp
# IQON : factory package list symbolic link
	ln -s /var/lib/opkg ${D}/usr/lib/ipkg
	ln -s libcrypto.so.0.9.8 ${D}/lib/libcrypto.so.0.9.7
}
