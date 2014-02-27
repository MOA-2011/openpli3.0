PRINC = "3"

SRC_URI_append = " file://rcS" 

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

do_install_append() {
	install -m 0755    ${WORKDIR}/rcS		${D}${sysconfdir}/init.d/rcS
}
