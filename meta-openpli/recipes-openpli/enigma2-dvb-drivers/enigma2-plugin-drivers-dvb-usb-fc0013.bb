DESCRIPTION = "USB DVB driver for fc0013 chipsets"

require conf/license/openpli-gplv2.inc

DVBPROVIDER ?= "kernel"

RDEPENDS_${PN} = " \
	${DVBPROVIDER}-module-dvb-usb-fc0013 \
	"

PV = "1.0"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"
