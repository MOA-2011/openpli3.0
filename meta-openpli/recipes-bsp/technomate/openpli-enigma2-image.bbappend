DEPENDS += " \
		enigma2-oe-plugins \
"
ENIGMA2_PLUGINS += " enigma2-plugin-extensions-autobouquets \
				enigma2-plugin-systemplugins-crossepg \
				enigma2-plugin-extensions-mediaplayer \				
				enigma2-plugin-extensions-mediascanner \
				enigma2-plugin-extensions-ppanel \				
				enigma2-plugin-extensions-isettinge2 \
				enigma2-plugin-extensions-mytube \
				enigma2-plugin-extensions-cooltvguide \
				enigma2-plugin-systemplugins-fastscan \
				enigma2-plugin-systemplugins-hotplug \
				enigma2-plugin-systemplugins-networkbrowser \
				enigma2-plugin-systemplugins-positionersetup \
				enigma2-plugin-systemplugins-satfinder \
				enigma2-plugin-systemplugins-skinselector \
				enigma2-plugin-systemplugins-softwaremanager \
				enigma2-plugin-systemplugins-videomode \
				enigma2-plugin-systemplugins-videotune \
				enigma2-plugin-systemplugins-wirelesslan \
				vuplus-opera-browser-util \
				vuplus-opera-dumpait \
				enigma2-plugin-extensions-hbbtv \
				enigma2-plugin-extensions-permanenttimeshift \
				\
				${@base_contains("MACHINE_FEATURES", "osdposition", "enigma2-plugin-systemplugins-osdpositionsetup", "",d)} \
				${@base_contains("MACHINE_FEATURES", "fan", "enigma2-plugin-systemplugins-tempfancontrol", "", d)} \
				\
				${@base_contains("BRAND_NAME","Technomate", "", "enigma2-plugin-extensions-shoutcast \
				enigma2-plugin-extensions-openwebif \
				enigma2-plugin-systemplugins-hdmicec \
				enigma2-plugin-extensions-autobackup \
				enigma2-plugin-extensions-pictureplayer \
				enigma2-plugin-extensions-audiosync", d)} \
				"
ENIGMA2_OPTIONAL += " \
				 enigma2-plugin-extensions-shoutcast \
				 enigma2-plugin-extensions-permanenttimeshift \
				 enigma2-plugin-extensions-openwebif \
				 enigma2-plugin-systemplugins-hdmicec \
				 enigma2-plugin-extensions-autobackup \
				 enigma2-plugin-extensions-pictureplayer \
				 enigma2-plugin-extensions-audiosync \
"

IMAGE_INSTALL += "enigma2-plugin-softcams-newcs \
			  enigma2-plugin-softcams-newcs-config \
			  enigma2-plugin-softcams-mgcamd \
			  enigma2-plugin-softcams-cccam221 \
			  enigma2-plugin-softcams-cccam-config \
			  python-json \
			  "
