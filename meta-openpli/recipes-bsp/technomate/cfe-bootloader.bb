DESCRIPTION = "iqon and technomate bootloader cfe"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

inherit deploy

PR = "r4"

S = "${WORKDIR}"

SRC_URI = " \
		   ${@base_contains("BRAND_NAME", "4D", "file://cfe-${MACHINE}.bin", "",d)} \
		   "
do_deploy() {
	install -m 0644 cfe-${MACHINE}.bin ${DEPLOYDIR}/
}

addtask deploy before do_build after do_install
