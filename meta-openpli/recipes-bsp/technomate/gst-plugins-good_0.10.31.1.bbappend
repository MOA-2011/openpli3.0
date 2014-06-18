SRC_URI += " \
			${@base_contains('BRAND_NAME', 'Technomate', 'file://fix_v4l2_cid_hcenter_deprecated.patch', '', d)} \
			${@base_contains('BRAND_NAME', 'Edision', 'file://fix_v4l2_cid_hcenter_deprecated.patch', '', d)} \
			${@base_contains('BRAND_NAME', 'Iqon', 'file://fix_v4l2_cid_hcenter_deprecated.patch', '', d)} \
			${@base_contains('BRAND_NAME', 'Mediabox', 'file://fix_v4l2_cid_hcenter_deprecated.patch', '', d)} \
			"
