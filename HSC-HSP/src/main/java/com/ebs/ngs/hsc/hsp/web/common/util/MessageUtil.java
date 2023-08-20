package com.ebs.ngs.hsc.hsp.web.common.util;

import com.ebs.ngs.core.helper.AppBaseModuleHelper;

public class MessageUtil {
	
	public static String getMessage(String messageCode) {
		return AppBaseModuleHelper.getBaseModule().getMessageReader().getMessage(messageCode);
	}
}
