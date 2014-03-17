DESCRIPTION = "Compatibility for packages that link to libcrypto or libssl 0.9.7"

require conf/license/openpli-gplv2.inc

AUTO_LIBNAME_PKGS = ""
PACKAGE_ARCH = "all"
RDEPENDS_${PN} = "libcrypto libssl"
RPROVIDES_${PN} = "libcrypto${PV} libssl${PV}"
PR = "r4"
SRC_URI = ""
S = "${WORKDIR}"

do_configure () {
	true
}

do_compile () {
	true
}

FILES_${PN} = "/usr/lib/"
