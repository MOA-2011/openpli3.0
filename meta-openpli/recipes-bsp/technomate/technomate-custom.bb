SRC_URI = "file://ld.so.conf \
		   file://rcS \
		   file://profile \
		   file://satellites.xml \
		   "

do_install () {
	install -d ${D}${sysconfdir}/init.d \
			   ${D}${sysconfdir}/default \
			   ${D}/etc/tuxbox
	
	install -d ${D}/lib
	ln -s libcrypto.so.0.9.8 ${D}/lib/libcrypto.so.0.9.7
	ln -s libssl.so.0.9.8 ${D}/lib/libssl.so.0.9.7

	install -m 0755 ${WORKDIR}/profile ${D}${sysconfdir}/enigma2.profile
	install -m 0755 ${WORKDIR}/rcS ${D}${sysconfdir}/rcS
	install -m 0755 ${WORKDIR}/ld.so.conf ${D}/etc
	install -m 0755 ${WORKDIR}/satellites.xml ${D}/etc/tuxbox
}
