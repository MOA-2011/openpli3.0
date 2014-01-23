ENIGMA2_PLUGINS = " \
					enigma2-plugin-systemplugins-crossepg \
					enigma2-plugin-extensions-autobackup \
					enigma2-plugin-extensions-cutlisteditor \
					enigma2-plugin-extensions-graphmultiepg \
					enigma2-plugin-extensions-mediaplayer \
					enigma2-plugin-extensions-mediascanner \
					enigma2-plugin-extensions-openwebif \
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
					${@base_contains("MACHINE_FEATURES", "hdmicec", "enigma2-plugin-systemplugins-hdmicec" , "", d)} \
					${@base_contains("MACHINE_FEATURES", "osdposition", "enigma2-plugin-systemplugins-osdpositionsetup" , "", d)} \
					\
					${@base_contains('OPENPLI_FEATURES', 'fan', 'enigma2-plugin-systemplugins-tempfancontrol', '', d)} \
					enigma2-plugin-systemplugins-networkwizard \
				"

IMAGE_INSTALL += " \
		enigma2-plugin-softcams-newcs \
		enigma2-plugin-softcams-newcs-config \
		enigma2-plugin-softcams-mgcamd \
		enigma2-plugin-softcams-cccam221 \
		enigma2-plugin-softcams-cccam-config \
				  "
