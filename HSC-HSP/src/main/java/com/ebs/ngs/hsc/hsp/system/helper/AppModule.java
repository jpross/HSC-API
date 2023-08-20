package com.ebs.ngs.hsc.hsp.system.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ebs.ngs.core.helper.AppBaseModule;

/**
 * 모듈에 대한 전역변수를 사용하기 위해서 
 * 1. com.ebs.ngs.core.helper.AppModule 을 상속
 * 2. 자신에 대해서 static 변수에 할 당합니다.
 * 3. 개발시 추가되는 공통 모듈에 대해서 아래 내용과 같이 추가합니다.
 * For Example (Add Module) 
 * <pre>
 *  @Autowired
 *	private CustomProperty customProperty;
 *	public CustomProperty getCustomProperty() {
 *		return customProperty;
 *	}
 * </pre>
 * 4. 작성된 모듈에 대해서 외부 모듈에서 사용법
 * For Example Usage
 * <pre>
 *  AppModule.getModule().getCustomProperty()...
 * </pre>
 * 
 * @author jisuk choi
 * @version 1.0
 */

@Component
@Qualifier(value = "appModule")
public class AppModule extends AppBaseModule {
	
	/**
	 * ------------  모듈 추가   ----------
	 * 아래 공통 모듈들을 추가
	 */
	/*
	@Autowired
	private CustomProperty customProperty;
	public CustomProperty getCustomProperty() {
		return customProperty;
	}
	*/
	
	@Autowired
	private RestTemplate restTemplate;
	
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
}
