DESCRIPTION = "USB DVB driver for RTL29xx devices"

require conf/license/openpli-gplv2.inc

DVBPROVIDER ?= "kernel"

RDEPENDS_${PN} = " \
	${DVBPROVIDER}-module-dvb-usb-rtl28xxu\
	"

PV = "1.0"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"
