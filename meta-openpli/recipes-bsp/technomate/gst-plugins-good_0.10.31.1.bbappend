SRC_URI += " \
			${@base_contains('BRAND_NAME', '4D', 'file://fix_v4l2_cid_hcenter_deprecated.patch', '', d)} \
			${@base_contains('BRAND_NAME', 'EDITION', 'file://fix_v4l2_cid_hcenter_deprecated.patch', '', d)} \
			${@base_contains('BRAND_NAME', 'MEDIABOX', 'file://fix_v4l2_cid_hcenter_deprecated.patch', '', d)} \
			"
