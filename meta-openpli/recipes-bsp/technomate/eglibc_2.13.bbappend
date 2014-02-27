do_install_append() {
	echo "/lib" >> ${D}${sysconfdir}/ld.so.conf
	echo "/usr/lib" >> ${D}${sysconfdir}/ld.so.conf
}
