do_install() {
	echo "vm.min_free_kbytes=8192" >> ${WORKDIR}/sysctl.conf
	echo "vm.swappiness = 0" >> ${WORKDIR}/sysctl.conf
}
