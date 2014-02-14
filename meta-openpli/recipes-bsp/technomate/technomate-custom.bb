SRC_URI = "file://rcS \
		   file://profile \
		   "

do_install () {
	install -d ${D}${sysconfdir}/init.d \
			   ${D}${sysconfdir}/default
	
	install -d ${D}/lib
	ln -s libcrypto.so.0.9.8 ${D}/lib/libcrypto.so.0.9.7
	ln -s libssl.so.0.9.8 ${D}/lib/libssl.so.0.9.7

	cp ${WORKDIR}/profile ${D}${sysconfdir}/enigma2.profile
	cp ${WORKDIR}/rcS ${D}${sysconfdir}/rcS
}
