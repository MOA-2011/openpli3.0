require openpli-image.bb
require openpli-dvb-usb.bb
require openpli-wifi.bb

ENIGMA2_PLUGINS += " \
	enigma2-plugin-extensions-cutlisteditor \
	enigma2-plugin-extensions-graphmultiepg \
	enigma2-plugin-extensions-mediaplayer \
	enigma2-plugin-extensions-mediascanner \
	enigma2-plugin-extensions-ppanel \
	\
	enigma2-plugin-pli-softcamsetup \
	\
	enigma2-plugin-systemplugins-fastscan \
	enigma2-plugin-systemplugins-hotplug \
	enigma2-plugin-systemplugins-networkbrowser \
	enigma2-plugin-systemplugins-positionersetup \
	enigma2-plugin-systemplugins-satfinder \
	enigma2-plugin-systemplugins-skinselector \
	enigma2-plugin-systemplugins-softwaremanager \
	enigma2-plugin-systemplugins-videomode \
	enigma2-plugin-systemplugins-videotune \
	\
	${@base_contains("MACHINE_FEATURES", "3dtv", "enigma2-plugin-systemplugins-osd3dsetup" , "", d)} \
	${@base_contains("MACHINE_FEATURES", "dvb-c", "enigma2-plugin-systemplugins-cablescan" , "", d)} \
	${@base_contains("MACHINE_FEATURES", "frontprocessor", "enigma2-plugin-systemplugins-frontprocessorupgrade" , "", d)} \
	${@base_contains("MACHINE_FEATURES", "osdposition", "enigma2-plugin-systemplugins-osdpositionsetup" , "", d)} \
	\
	${@base_contains('OPENPLI_FEATURES', 'ci', 'enigma2-plugin-systemplugins-commoninterfaceassignment', '', d)} \
	${@base_contains('OPENPLI_FEATURES', 'dvd', 'enigma2-plugin-extensions-cdinfo enigma2-plugin-extensions-dvdplayer', '', d)} \
	${@base_contains('OPENPLI_FEATURES', 'fan', 'enigma2-plugin-systemplugins-tempfancontrol', '', d)} \
	"

DEPENDS = " \
	enigma2 \
	enigma2-pliplugins \
	enigma2-plugins \
	"

ENIGMA2_OPTIONAL = " \
	channelsettings-enigma2-meta \
	enigma2-plugin-drivers-usbserial \
	enigma2-plugin-extensions-ambx \
	enigma2-plugin-extensions-et-livestream \
	enigma2-plugin-extensions-openuitzendinggemist \
	enigma2-plugin-extensions-tuxcom \
	enigma2-plugin-extensions-tuxterm \
	enigma2-plugin-extensions-xmltvimport \
	enigma2-plugin-security-firewall \
	enigma2-plugin-skins-pli-hd \
	enigma2-plugin-skins-pli-scale-hd \
	enigma2-plugins \
	enigma2-skins \
	picons-enigma2-meta \
	softcams-enigma2-meta \
	task-openplugins \
	${@base_contains("MACHINE_FEATURES", "blindscan-dvbs", "enigma2-plugin-systemplugins-satscan" , "", d)} \
	dvb-usb-drivers-meta \
	cdfs cdtextinfo \
	meta-enigma2-dvdburn \
	"

IMAGE_INSTALL += " \
	aio-grab \
	enigma2 \
	python-compression \
	python-textutils \
	python-pyopenssl \
	libavahi-client \
	openssl \
	kernel-params \
	settings-autorestore \
	tuxbox-common \
	${ENIGMA2_PLUGINS} \
	\
	${@base_contains("MACHINE_FEATURES", "tpm", "tpmd", "", d)} \
	${@base_contains('MACHINE_FEATURES', 'pci', 'madwifi-ng madwifi-ng-modules', '',d)} \
	${@base_contains("MACHINE_FEATURES", "wifiusblegacy", "${WIFI_DRIVERS}", "", d)} \
	${@base_contains("MACHINE_FEATURES", "dvbusb", "${DVB_USB_DRIVERS}", "",d)} \
	\
	${@base_contains('OPENPLI_FEATURES', 'dvd', 'cdfs cdtextinfo kernel-module-isofs kernel-module-udf', '', d)} \
	${@base_contains('OPENPLI_FEATURES', 'libpassthrough', 'libpassthrough', '', d)} \
	\
	\
	"
#	kernel-module-ftdi-sio 

OPTIONAL_PACKAGES += " \
	${ENIGMA2_OPTIONAL} \
	"

export IMAGE_BASENAME = "openpli-enigma2"
