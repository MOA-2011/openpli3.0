SRC_URI += "file://CCcam.xml \
		   file://PPanel_tutorial.xml \
		   "

do_install_append() {
	install -d 0755 ${D}/etc/ppanels/
	install -m 0644 ${WORKDIR}/CCcam.xml ${D}/etc/ppanels 
	install -m 0644 ${WORKDIR}/PPanel_tutorial.xml ${D}/etc/ppanels
}
