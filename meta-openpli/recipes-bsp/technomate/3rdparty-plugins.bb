DESCRIPTION = "3rd Party plugins for Enigma2"
MAINTAINER = "4D"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "CLOSED"

inherit gitpkgv

SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"
PR = "r14"

SRC_URI="git://github.com/MOA-2011/plugins-patch.git;protocol=git"

EXTRA_OECONF = " \
			 BUILD_SYS=${BUILD_SYS} \
			 HOST_SYS=${HOST_SYS} \
			 STAGING_INCDIR=${STAGING_INCDIR} \
			 STAGING_LIBDIR=${STAGING_LIBDIR} \
			 --with-boxtype=${MACHINE} \
"

ALLOW_EMPTY_${PN} = "1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit autotools deploy

S = "${WORKDIR}/git"

do_install() {
}

do_deploy() {
	install -d 0644 ${WORKDIR}/deploy-ipks/${MACHINE_ARCH}
#	install -m 0644 *.ipk ${WORKDIR}/deploy-ipks/${MACHINE_ARCH}
#    	install -m 0644 enigma2-plugin-extensions-xmodem_1.0* ${WORKDIR}/deploy-ipks/${MACHINE_ARCH}
#	install -m 0644 enigma2-plugin-pli-softcamsetup_3.0-r2_mips32el* ${WORKDIR}/deploy-ipks/${MACHINE_ARCH}
#	install -m 0644 enigma2-plugin-systemplugins-mountmanager_1.0-r5_mips32el* ${WORKDIR}/deploy-ipks/${MACHINE_ARCH}
	install -m 0644 font-valis-hd_2010.05.14-r0* ${WORKDIR}/deploy-ipks/${MACHINE_ARCH}

#	install ${WORKDIR}/deploy-ipks/${MACHINE_ARCH}/enigma2-plugin-extensions-xmodem_1.0* ${DEPLOY_DIR_IPK}/${MACHINE_ARCH}
#	install ${WORKDIR}/deploy-ipks/${MACHINE_ARCH}/enigma2-plugin-pli-softcamsetup_3.0-r2_mips32el* ${DEPLOY_DIR_IPK}/${MACHINE_ARCH}
#	install ${WORKDIR}/deploy-ipks/${MACHINE_ARCH}/enigma2-plugin-systemplugins-mountmanager_1.0-r5_* ${DEPLOY_DIR_IPK}/${MACHINE_ARCH}
	install ${WORKDIR}/deploy-ipks/${MACHINE_ARCH}/font-valis-hd_2010.05.14-r0* ${DEPLOY_DIR_IPK}/${MACHINE_ARCH}
}
addtask deploy before do_build after do_install
