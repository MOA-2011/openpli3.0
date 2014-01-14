SRC_URI += " \
		file://keymap.xml \
		file://${MACHINE}.keymap.xml \
		file://enigma2_end.sh \
		file://enigma2_pre_start.sh \
		file://enigma2.sh \
		file://restore.sh \
		file://var \
		file://def_ins \
		file://ios.input_rcold-configured.png \
		file://ios.input_rcold.png \
		file://optimuss.input_rcold-configured.png \
		file://optimuss.input_rcold.png \
		file://input_rcold-configured.png \
		file://input_rcold.png \
		file://mediabox.input_rcold-configured.png \
		file://mediabox.input_rcold.png \
		file://menu-${MACHINE}.xml \
		file://setup.xml \
		file://e2settings \
		file://satellites.xml \
		file://mediabox.var \
		file://factory.var \
"

S = "${WORKDIR}/git"

do_configure_prepend() {
	cp ${WORKDIR}/keymap.xml ${S}/data
	cp ${WORKDIR}/menu-${MACHINE}.xml ${S}/data/menu.xml
}

do_install_append() {
	install -d 0755 ${D}/usr/bin/
		install -d 0755 ${D}/etc/tuxbox/
		install -d 0755 ${D}/etc/enigma2/
		install -d 0755 ${D}/var/
		install -d 0755 ${D}/usr/share/enigma2/skin_default/icons/
#		install -m 0755 ${WORKDIR}/enigma2_end.sh ${D}/usr/bin/
#		install -m 0755 ${WORKDIR}/enigma2_pre_start.sh ${D}/usr/bin/
#		install -m 0755 ${WORKDIR}/enigma2.sh ${D}/usr/bin/
		install -m 0755 ${WORKDIR}/setup.xml ${D}/usr/share/enigma2/

		ln -s /usr/bin/opkg ${D}/usr/bin/ipkg
		ln -s /etc/tuxbox ${D}/var/tuxbox

		cp ${WORKDIR}/var ${D}/etc/var.tar
		cp ${WORKDIR}/factory.var ${D}/etc/factory.var.tar
		cp ${WORKDIR}/menu-${MACHINE}.xml ${D}/usr/share/enigma2/menu.xml

		if [ "${MACHINE}" = tmnano2t ];then
			cp ${WORKDIR}/${MACHINE}.keymap.xml ${D}/usr/share/enigma2/keymap.xml
		else
			cp ${WORKDIR}/keymap.xml ${D}/usr/share/enigma2/keymap.xml
		fi

		tar xf ${WORKDIR}/def_ins -C ${WORKDIR}/
		mv ${WORKDIR}/def_inst ${D}/etc/.def_inst

		cp ${WORKDIR}/e2settings ${D}/etc/.e2settings.tar
		cp ${WORKDIR}/satellites.xml ${D}/etc/tuxbox

		if [ "${BRAND_NAME}" = "Mediabox" ]; then
			install -m 0755 ${WORKDIR}/mediabox.input_rcold.png ${D}/usr/share/enigma2/skin_default/icons/input_rcold.png
			install -m 0755 ${WORKDIR}/mediabox.input_rcold-configured.png ${D}/usr/share/enigma2/skin_default/icons/input_rcold-configured.png
			cp -rf ${WORKDIR}/mediabox.var ${D}/etc/mediabox.var # factory image
		elif [ "${BRAND_NAME}" = "Iqon" ]; then
			install -m 0755 ${WORKDIR}/ios.input_rcold-configured.png ${D}/usr/share/enigma2/skin_default/icons/input_rcold-configured.png
			install -m 0755 ${WORKDIR}/ios.input_rcold.png ${D}/usr/share/enigma2/skin_default/icons/input_rcold.png
		elif [ "${BRAND_NAME}" = "Edition" ]; then
			install -m 0755 ${WORKDIR}/optimuss.input_rcold-configured.png ${D}/usr/share/enigma2/skin_default/icons/input_rcold-configured.png
			install -m 0755 ${WORKDIR}/optimuss.input_rcold.png ${D}/usr/share/enigma2/skin_default/icons/input_rcold.png
		elif [ "${MACHINE}" = "tmtwinoe" ]; then
			touch ${D}/etc/.ci					# IQON : TMtwinoe default ci yes.
		else
			install -m 0755 ${WORKDIR}/input_rcold.png ${D}/usr/share/enigma2/skin_default/icons/
			install -m 0755 ${WORKDIR}/input_rcold-configured.png ${D}/usr/share/enigma2/skin_default/icons/
		fi
}
