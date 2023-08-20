/**
 * 0. Project  : EBS 공통API 기반 고교강의 재구축 및 패밀리사이트 클라우드 전환 개발 사업
 * 1. Package : com.ebs.ngs.skeleton.web.controller
 * 2. FileName : WebController.java
 * 3. 작성자  : osc
 * 4. 작성일  : 2020. 9. 16 오후 7:37:43
 * 5. 변경이력 
 * -------------------------------------------------------------------
 *    이름		:     일자		:    변경내용 (근거자료)
 * -------------------------------------------------------------------
 *  osc 	: 	2020. 9. 16	: 신규 개발.
 */

package com.ebs.ngs.hsc.hsp.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ebs.ngs.core.api.response.ApiHeader;
import com.ebs.ngs.core.api.response.ApiMessage;
import com.ebs.ngs.core.controller.BaseWebController;
import com.ebs.ngs.core.property.ApiUriMapsWrapper;
import com.ebs.ngs.core.util.ApiCallUtil;
import com.ebs.ngs.core.util.CookieUtil;
import com.ebs.ngs.core.util.JsonUtil;
import com.ebs.ngs.core.util.WebUtil;
import com.ebs.ngs.hsc.hsp.system.helper.AppModuleHelper;

/**
* <pre>
*  웹 컨트롤로 공용유틸리티 helper
* </pre>
* 
* @Author  : osc
* @Date    : 2020. 9. 16
* @Version : 1.0
*/

public class WebController extends BaseWebController {
	
	/**
	 * AppModuleHelper 를 통해서 각종 Utility Module 에 접근편이성을 높이기 위해 정의
	 */
	public final static WebUtil webUtil () {
		return AppModuleHelper.getModule().getWebUtil();
	}
	
	/**
	* <PRE>
	*  json util
	* </PRE>
	* @return
	 */
	public final static JsonUtil jsonUtil() {
		return AppModuleHelper.getModule().getJsonUtil();
	}
	
	/**
	* <PRE>
	*  쿠키 처리 유틸리티
	* </PRE>
	* @return
	 */
	public final static CookieUtil cookieUtil() {
		return AppModuleHelper.getModule().getCookieUtil();
	}
	
	/**
	* <PRE>
	*  현재 사용자의 tocken 값을 읽어들인다.
	* </PRE>
	* @param request
	* @return
	 */
	public final static String getTocken(HttpServletRequest request) {
		return cookieUtil().getValue(request, AppModuleHelper.JWT_TOKEN_HEADER_PREFIX);
	}
	
	/**
	* <PRE>
	*  Api uri 정보를 담고 있는 개체
	*   - property 파일에 존재
	* </PRE>
	* @return
	 */
	public final static ApiUriMapsWrapper apiUriMapsWrapper() {
		return AppModuleHelper.getModule().getMandatoryProperty().getApiUriMapsWrapper();
	}
	
	/**
	* <PRE>
	*  Api Call Utility
	* </PRE>
	* @return
	 */
	public final static ApiCallUtil apiCallUtil() {
		return AppModuleHelper.getModule().getApiCallUtil();
	}
	
	/**
	* <PRE>
	*  token를 cookie 에 저장하기 위한 유효 초 
	* </PRE>
	* @return
	 */
	public final static int getCookieValidSecond() {
		return (int) AppModuleHelper.getModule().getMandatoryProperty().getCookieValidSecond();
	}
	
	
	/**
	* <PRE>
	*  기본 Api 정상 응답코드
	* </PRE>
	* @return
	 */
	public final static String getApiSuccessCode() {
		return AppModuleHelper.getModule().getMandatoryProperty().getApiSuccessCode();
	}
	
	public final static String headerKey = ApiCallUtil.API_HEADER_KEY;
	/**
	* <PRE>
	*  공통 Api Response Header 설정
	* </PRE>
	 */
	public final static void setApiHeaderResponse(HttpServletResponse response, ApiHeader apiHeader) {
		try {
	    	response.addHeader(headerKey, apiCallUtil().getJsonString(apiHeader));
		} catch (Exception e) {
			
		}
	}
	
	/**
	* <PRE>
	*  Api 요청 결과
	* </PRE>
	* @param <T>
	* @param resEntity
	* @return
	 */
	public final static <T, K> boolean isOkApiCall(ResponseEntity<ApiMessage<T, K>> resEntity) {
		
		if (resEntity != null && resEntity.getStatusCode().equals(HttpStatus.OK) && resEntity.getBody() != null
			&& resEntity.getBody().getResponse().getCode().equals(getApiSuccessCode()) ) {
			return Boolean.TRUE; 
		} else {
			return Boolean.FALSE;
		}
		
	}
	
	/**
	* <PRE>
	*  Api 요청 결과가 정상이지 않지만, 응답메시지가 존재하는 경우
	* </PRE>
	* @param <T>
	* @param resEntity
	* @return
	 */
	public final static <T, K> boolean existsResponse(ResponseEntity<ApiMessage<T, K>> resEntity) {
		
		if (resEntity != null && resEntity.getStatusCode().equals(HttpStatus.OK) && resEntity.getBody() != null) {
			return Boolean.TRUE; 
		} else {
			return Boolean.FALSE;
		}
		
	}
	
}
