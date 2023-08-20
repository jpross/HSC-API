package com.ebs.ngs.hsc.hsp.system.helper;

import com.ebs.ngs.core.helper.AppBaseModuleHelper;

/**
 * 공통 헬퍼를 상속받아 각 프로젝트 모듈로부터 상속받습니다.
 * 필요시 확장을 합니다.
 * 
 * 작성단계
 *  1. AppModule 상속받아 작성 @see AppModule
 *  2. AppModuleHelper 상속
 * For 필수 작성부  
 * <pre>
 * private static AppModule appModule;
 *	public static AppModule getModule() {
 *		if (appModule==null) {
 *			appModule = AppModuleHelper.getAutowireCapableBeanFactory().getBean(AppModule.class);
 *		}
 *		return appModule;
 *	}
 * </pre>
 * 
 * @author jisuk Choi
 * @version 1.0
 */
public class AppModuleHelper extends AppBaseModuleHelper {
	
	/**
	 * 상속 이후 필수 작성부
	 */
	private static AppModule appModule;
	public static AppModule getModule() {
		
		if (appModule==null) {
			appModule = AppModuleHelper.getAutowireCapableBeanFactory().getBean("appModule", AppModule.class);
		}
		
		return appModule;
		
	}
	
}
