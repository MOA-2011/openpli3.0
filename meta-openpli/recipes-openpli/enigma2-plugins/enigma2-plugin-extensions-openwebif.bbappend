PRINC = "2"

python do_package_prepend () {
	boxtypes = [
		('dm500hd', 'dm500hd.jpg', 'dm_normal.png'),
		('dm7020hd', 'unknown.jpg', 'dm_normal.png'),
		('dm8000', 'dm8000.jpg', 'dm_normal.png'),
		('dm800se', 'dm800se.jpg', 'dm_normal.png'),
		('et5x00', 'et5x00.jpg', 'et_rc5_normal.png'),
		('et6x00', 'et5x00.jpg', 'et_rc5_normal.png'),
		('et9x00', 'et9x00.jpg', 'et_rc7_normal.png'),
		('et4x00', 'unknown.jpg', 'et_rc13_normal.png'),
		('xp1000', 'unknown.jpg', 'xp_rc14_normal.png'),
		('vuduo', 'duo.jpg', 'vu_normal.png'),
		('vusolo', 'solo.jpg', 'vu_normal.png'),
		('vuultimo', 'ultimo.jpg', 'vu_ultimo.png'),
		('vuuno', 'uno.jpg', 'vu_normal.png'),
		('tmtwinoe', 'tmtwinoe.jpg', 'tmtwinoe.png'),
		('tm2toe', 'tm2toe.jpg', 'tm2toe.png'),
		('tmsingle', 'tmsingle.jpg', 'tmsingle.png'),
		('tmnano2t', 'tmnano2t.jpg', 'tmnano2t.png'),
		('tmnanooe', 'tmnanooe.jpg', 'tmnanooe.png'),
		('ios100', 'ios100.jpg', 'ios100.png'),
		('ios200', 'ios200.jpg', 'ios200.png'),
		('ios300', 'ios300.jpg', 'ios300.png'),
		('force1', 'force1.jpg', 'force1.png'),
		('force1plus', 'force1plus.jpg', 'force1plus.png'),
		('force2', 'force2.jpg', 'force2.png'),
		('optimussos1', 'optimussos1.jpg', 'optimussos1.png'),
		('optimussos1plus', 'optimussos1plus.jpg', 'optimussos1plus.png'),
		('optimussos2', 'optimussos2.jpg', 'optimussos2.png'),
		('optimussos2plus', 'optimussos2plus.jpg', 'optimussos2plus.png'),
		('tmnano2super', 'tmnano2super.jpg', 'tmnano2super.png'),
		('mediabox', 'mediabox.jpg', 'mediabox.png'),
	]
	import os
	top = '${D}${PLUGINPATH}/public/images/'
	target_box = 'unknown.jpg'
	target_remote = 'ow_remote.png'
	for x in boxtypes:
		if x[0] == '${MACHINE}':
			target_box = x[1]
			target_remote = x[2]
			break
	for root, dirs, files in os.walk(top + 'boxes', topdown=False):
		for name in files:
			if target_box != name and name != 'unknown.jpg':
				os.remove(os.path.join(root, name))
	for root, dirs, files in os.walk(top + 'remotes', topdown=False):
		for name in files:
			if target_remote != name and name != 'ow_remote.png':
				os.remove(os.path.join(root, name))
}
