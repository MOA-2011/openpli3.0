DESCRIPTION = "iqon and technomate bootloader cfe"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

PR = "r4"

S = "${WORKDIR}"

SRC_URI += "file://cfe-${MACHINE}.bin"

do_install() {
	install -m 0644 cfe-${MACHINE}.bin ${DEPLOY_DIR_IMAGE}/
}
