require openpli-image.bb

WIFI_DRIVERS = " \
	firmware-carl9170 \
	firmware-htc7010 \
	firmware-htc9271 \
	firmware-rt2870 \
	firmware-rt73 \
	firmware-rtl8192cu \
	firmware-rtl8712u \
	firmware-zd1211 \
	\
	kernel-module-r8712u \
	kernel-module-rt2500usb \
	kernel-module-rt73usb \
	kernel-module-rtl8187 \
	kernel-module-zd1211rw \
	rtl8192cu \
	"

DVB_USB_DRIVERS = " \
	firmware-dvb-usb-dib0700-1.20 \
	firmware-dvb-usb-dibusb-5.0.0.11 \
	firmware-dvb-usb-dibusb-6.0.0.8 \
	firmware-dvb-usb-dibusb-an2235-01 \
	firmware-xc3028-v27 \
	firmware-xc3028l-v36 \
	firmware-dvb-siano \
	firmware-dvb-nova-12mhz-b0 \
	firmware-isdbt-nova-12mhz-b0 \
	firmware-dvb-fe-tda10071 \
	firmware-dvb-usb-s660 \
	firmware-dvb-fe-ds3000 \
	firmware-dvb-usb-it913x \
	firmware-as102-data1-st \
	firmware-as102-data2-st \
	firmware-dvb-usb-dtt200u-01 \
	firmware-dvb-usb-wt220u-02 \
	firmware-dvb-usb-wt220u-fc03 \
	firmware-dvb-usb-wt220u-miglia-01 \
	firmware-dvb-usb-wt220u-zl0353-01 \
	firmware-dvb-usb-af9035-02 \
	firmware-dvb-usb-it913x \
	firmware-dvb-usb-af9015 \
	firmware-dvb-fe-af9013 \
	\
	kernel-module-a8293 \
	kernel-module-af9013 \
	kernel-module-af9033 \
	kernel-module-ah6 \
	kernel-module-appletalk \
	kernel-module-at76c50x-usb \
	kernel-module-atbm8830 \
	kernel-module-au0828 \
	kernel-module-au8522-common \
	kernel-module-au8522-dig \
	kernel-module-authenc \
	kernel-module-authencesn \
	kernel-module-b2c2-flexcop \
	kernel-module-b2c2-flexcop-usb \
	kernel-module-bcm203x \
	kernel-module-bcm3510 \
	kernel-module-bfusb \
	kernel-module-bnep \
	kernel-module-bpa10x \
	kernel-module-brd \
	kernel-module-bsd-comp \
	kernel-module-cbc \
	kernel-module-cdc-acm \
	kernel-module-crc7 \
	kernel-module-cx22700 \
	kernel-module-cx22702 \
	kernel-module-cx24110 \
	kernel-module-cx24113 \
	kernel-module-cx24116 \
	kernel-module-cx24123 \
	kernel-module-cxd2820r \
	kernel-module-dib0070 \
	kernel-module-dib0090 \
	kernel-module-dib3000mb \
	kernel-module-dib3000mc \
	kernel-module-dib7000m \
	kernel-module-dib7000p \
	kernel-module-dib8000 \
	kernel-module-dib9000 \
	kernel-module-dibx000-common \
	kernel-module-drxd \
	kernel-module-drxk \
	kernel-module-ds3000 \
	kernel-module-dsa-core \
	kernel-module-dvb-dummy-fe \
	kernel-module-dvb-pll \
	kernel-module-dvb-ttusb-budget \
	kernel-module-dvb-usb-af9015 \
	kernel-module-dvb-usb-af9035 \
	kernel-module-dvb-usb-anysee \
	kernel-module-dvb-usb-au6610 \
	kernel-module-dvb-usb-az6007 \
	kernel-module-dvb-usb-ce6230 \
	kernel-module-dvb-usb-cypress-firmware \
	kernel-module-dvb-usb-ec168 \
	kernel-module-dvb-usb-gl861 \
	kernel-module-dvb-usb-it913x \
	kernel-module-dvb-usb-mxl111sf \
	kernel-module-dvb-usb-rtl28xxu \
	kernel-module-dvb-usb-v2 \
	kernel-module-rt2800usb \
	kernel-module-rt2x00usb \
	kernel-module-rtl2830 \
	kernel-module-rtl2832 \
	kernel-module-rts5139 \
	kernel-module-si21xx \
	kernel-module-sit \
	kernel-module-slhc \
	kernel-module-smsdvb \
	kernel-module-smsmdtv \
	kernel-module-smsusb \
	kernel-module-sp8870 \
	kernel-module-sp887x \
	kernel-module-ssb \
	kernel-module-stb0899 \
	kernel-module-stb6000 \
	kernel-module-stb6100 \
	kernel-module-stv0288 \
	kernel-module-stv0297 \
	kernel-module-stv0299 \
	kernel-module-stv0367 \
	kernel-module-stv0900 \
	kernel-module-stv090x \
	kernel-module-stv6110 \
	kernel-module-stv6110x \
	kernel-module-tda10021 \
	kernel-module-tda10023 \
	kernel-module-tda10048 \
	kernel-module-tda1004x \
	kernel-module-tda10071 \
	kernel-module-tda10086 \
	kernel-module-tda18212 \
	kernel-module-tda18218 \
	kernel-module-tda18271 \
	kernel-module-tda18271c2dd \
	kernel-module-tda665x \
	kernel-module-tda8083 \
	kernel-module-tda8261 \
	kernel-module-tda826x \
	kernel-module-tda827x \
	kernel-module-tda8290 \
	kernel-module-tda9887 \
	kernel-module-tea5761 \
	kernel-module-tea5767 \
	kernel-module-ts2020 \
	kernel-module-ts-kmp \
	kernel-module-ttusb-dec \
	kernel-module-ttusbdecfe \
	kernel-module-tua6100 \
	kernel-module-tua9001 \
	kernel-module-tun \
	kernel-module-tuner-simple \
	kernel-module-tuner-types \
	kernel-module-tuner-xc2028 \
	kernel-module-tunnel4 \
	kernel-module-tunnel6 \
	kernel-module-tveeprom \
	kernel-module-ves1820 \
	kernel-module-ves1x93 \
	kernel-module-w35und \
	kernel-module-xc4000 \
	kernel-module-xc5000 \
	kernel-module-xfrm6-mode-beet \
	kernel-module-xfrm6-mode-ro \
	kernel-module-xfrm6-mode-transport \
	kernel-module-xfrm6-mode-tunnel \
	kernel-module-xfrm6-tunnel \
	kernel-module-xfrm-algo \
	kernel-module-xfrm-ipcomp \
	kernel-module-x-tables \
	kernel-module-xt-classify \
	kernel-module-xt-connmark \
	kernel-module-xt-hmark \
	kernel-module-xt-idletimer \
	kernel-module-xt-log \
	kernel-module-xt-mark \
	kernel-module-xt-nflog \
	kernel-module-xt-nfqueue \
	kernel-module-xt-rateest \
	kernel-module-xt-tcpmss \
	kernel-module-xt-tcpudp \
	kernel-module-xt-tee \
	kernel-module-zl10036 \
	kernel-module-zl10039 \
	kernel-module-zl10353 \
"

ENIGMA2_PLUGINS += " \
	enigma2-plugin-extensions-audiosync \
	enigma2-plugin-extensions-autobackup \
	enigma2-plugin-extensions-cutlisteditor \
	enigma2-plugin-extensions-graphmultiepg \
	enigma2-plugin-extensions-mediaplayer \
	enigma2-plugin-extensions-mediascanner \
	enigma2-plugin-extensions-openwebif \
	enigma2-plugin-extensions-pictureplayer \
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
	${@base_contains("MACHINE_FEATURES", "hdmicec", "enigma2-plugin-systemplugins-hdmicec" , "", d)} \
	${@base_contains("MACHINE_FEATURES", "osdposition", "enigma2-plugin-systemplugins-osdpositionsetup" , "", d)} \
	${@base_contains("MACHINE_FEATURES", "wifi", "enigma2-plugin-systemplugins-wirelesslan", "", d)} \
	\
	${@base_contains('OPENPLI_FEATURES', 'ci', 'enigma2-plugin-systemplugins-commoninterfaceassignment', '', d)} \
	${@base_contains('OPENPLI_FEATURES', 'dvd', 'enigma2-plugin-extensions-cdinfo enigma2-plugin-extensions-dvdplayer', '', d)} \
	${@base_contains('OPENPLI_FEATURES', 'fan', 'enigma2-plugin-systemplugins-tempfancontrol', '', d)} \
	"

DEPENDS += " \
	enigma2 \
	enigma2-pliplugins \
	enigma2-plugins \
	"

ENIGMA2_OPTIONAL = " \
	channelsettings-enigma2-meta \
	enigma2-pliplugins \
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
	python-textutils \
	libavahi-client \
	openssl \
	kernel-params \
	settings-autorestore \
	tuxbox-common \
	${ENIGMA2_PLUGINS} \
	\
	${@base_contains("MACHINE_FEATURES", "tpm", "tpmd", "", d)} \
	${@base_contains("MACHINE_FEATURES", "wifi", "${WIFI_DRIVERS}", "", d)} \
	${@base_contains("MACHINE_FEATURES", "dvbusb", "${DVB_USB_DRIVERS}", "", d)} \
	${@base_contains('MACHINE_FEATURES', 'pci', 'madwifi-ng madwifi-ng-modules', '',d)} \
	\
	${@base_contains('OPENPLI_FEATURES', 'dvd', 'cdfs cdtextinfo kernel-module-isofs kernel-module-udf', '', d)} \
	${@base_contains('OPENPLI_FEATURES', 'libpassthrough', 'libpassthrough', '', d)} \
	"

OPTIONAL_PACKAGES += " \
	${ENIGMA2_OPTIONAL} \
	"

export IMAGE_BASENAME = "openpli-enigma2"
