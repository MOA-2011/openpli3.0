MODULE = "OpenWebif"
DESCRIPTION = "Control your receiver with a browser"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://README;firstline=10;lastline=12;md5=9c14f792d0aeb54e15490a28c89087f7"

DEPENDS = "python-cheetah-native"
RDEPENDS_${PN} = "python-cheetah python-json python-unixadmin python-misc python-pyopenssl python-shell aio-grab"

inherit gitpkgv
PV = "0.1+git${SRCPV}"
PKGV = "0.1+git${GITPKGV}"
PR = "r0.72"

require openplugins-distutils.inc

SRC_URI += "file://fix_models.patch;striplevel=1 \
			file://${MACHINE}.html \
			file://${MACHINE}.jpg \
			file://${MACHINE}.png \
	"

# Just a quick hack to "compile" it
do_compile() {
	cheetah-compile -R --nobackup ${S}/plugin
	python -O -m compileall ${S}
}

PLUGINPATH = "/usr/lib/enigma2/python/Plugins/Extensions/${MODULE}"
do_install_append() {
	install -d ${D}${PLUGINPATH}
	cp -rp ${S}/plugin/* ${D}${PLUGINPATH}
	find ${D}${PLUGINPATH}/ -name '*.pyo' -exec rm {} \;
	if [ "${MACHINE}" = "ios100" -o "${MACHINE}" = "ios200" -o "${MACHINE}" = "ios300" ]; then
		cp -rp ${WORKDIR}/${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/${MACHINE}hd.jpg
		cp -rp ${WORKDIR}/${MACHINE}.png ${D}${PLUGINPATH}/public/images/remotes/${MACHINE}hd.png
		cp -rf ${WORKDIR}/${MACHINE}.html ${D}${PLUGINPATH}/public/static/remotes/${MACHINE}hd.html
	elif [ "${MACHINE}" = "mediabox" ]; then
		cp -rp ${WORKDIR}/${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/Mediabox\ HD\ LX-1.jpg
		cp -rp ${WORKDIR}/${MACHINE}.png ${D}${PLUGINPATH}/public/images/remotes/
		cp -rf ${WORKDIR}/${MACHINE}.html ${D}${PLUGINPATH}/public/static/remotes/
	elif [ "${MACHINE}" = "optimussos1" ]; then
		cp -rp ${WORKDIR}/${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/Optimuss\ OS1.jpg
		cp -rp ${WORKDIR}/${MACHINE}.png ${D}${PLUGINPATH}/public/images/remotes/
		cp -rf ${WORKDIR}/${MACHINE}.html ${D}${PLUGINPATH}/public/static/remotes/
	elif [ "${MACHINE}" = "optimussos2" ]; then
		cp -rp ${WORKDIR}/${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/Optimuss\ OS2.jpg
		cp -rp ${WORKDIR}/${MACHINE}.png ${D}${PLUGINPATH}/public/images/remotes/
		cp -rf ${WORKDIR}/${MACHINE}.html ${D}${PLUGINPATH}/public/static/remotes/
	elif [ "${MACHINE}" = "optimussos1plus" ]; then
		cp -rp ${WORKDIR}/${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/Optimuss\ OS1+.jpg
		cp -rp ${WORKDIR}/${MACHINE}.png ${D}${PLUGINPATH}/public/images/remotes/
		cp -rf ${WORKDIR}/${MACHINE}.html ${D}${PLUGINPATH}/public/static/remotes/
	elif [ "${MACHINE}" = "optimussos2plus" ]; then
		cp -rp ${WORKDIR}/${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/Optimuss\ OS2+.jpg
		cp -rp ${WORKDIR}/${MACHINE}.png ${D}${PLUGINPATH}/public/images/remotes/
		cp -rf ${WORKDIR}/${MACHINE}.html ${D}${PLUGINPATH}/public/static/remotes/
	elif [ "${MACHINE}" = "force1plus" ]; then
		cp -rp ${WORKDIR}/${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/force1+.jpg
		cp -rp ${WORKDIR}/${MACHINE}.png ${D}${PLUGINPATH}/public/images/remotes/
		cp -rf ${WORKDIR}/${MACHINE}.html ${D}${PLUGINPATH}/public/static/remotes/
	else
		cp -rp ${WORKDIR}/${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/
		cp -rp ${WORKDIR}/${MACHINE}.png ${D}${PLUGINPATH}/public/images/remotes/
		cp -rf ${WORKDIR}/${MACHINE}.html ${D}${PLUGINPATH}/public/static/remotes/
	fi
}

FILES_${PN} = "${PLUGINPATH}"
