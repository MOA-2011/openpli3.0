PRINC = "3"

SRC_URI_append = "  file://rcS \
			   	    file://profile \
					file://CCcam.xml \
					file://PPanel_tutorial.xml \
				 " 

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

do_install_append() {
	install -d ${D}${sysconfdir}/ppanels/
	install -m 0755 ${WORKDIR}/rcS ${D}${sysconfdir}/init.d/rcS
	install -m 0755 ${WORKDIR}/profile ${D}${sysconfdir}/enigma2.profile
	install -m 0755 ${WORKDIR}/CCcam.xml ${D}${sysconfdir}/ppanels/CCcam.xml
	install -m 0755 ${WORKDIR}/PPanel_tutorial.xml ${D}${sysconfdir}/ppanels/PPanel_tutorial.xml
}
