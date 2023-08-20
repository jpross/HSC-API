package com.ebs.ngs.hsc.hsp.web.api.application.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebs.biz.api.common.log.annotation.BizApiLog;
import com.ebs.biz.api.common.response.BizApiResponseCode;
import com.ebs.biz.api.common.response.BizApiResponseType;
import com.ebs.biz.api.common.response.BizVodApiResponse;
import com.ebs.biz.api.common.validate.ApiKeyValidate;
import com.ebs.ngs.hsc.hsp.web.api.application.service.ApiService;

@RestController 
@RequestMapping("/api/v1")
@Validated
public class ApiControllerV1 {
	
	@Autowired
	private ApiService apiService;
	
	/*
	 * API 인증키 검사는 ApiKeyValidator class에서 처리하고 있음 (현재 경기도 교육청만 체크 중 / 이후 다른 곳에서도 서비스 진행할 시 referer 또는 인증키와 매핑하여 체크하는 추가 로직 필요)
	 * 기본에러(ApiException) 및 파라메터 오류에 관한 exception은 ApiExceptionHandler class 참고
	 */

	@BizApiLog(bizApiResponseType=BizApiResponseType.VOD_URL, apiDsCd="VOD:LECT", requestItem="lectId")
	@GetMapping("lect/vod")
	public BizVodApiResponse lectVod(
			@RequestParam(value = "lectId", required = true) String lectId,
			@RequestParam(value = "apiKey", required = true) @Valid @ApiKeyValidate String apiKey) {
		
		String vodUrl = apiService.getLectVodUrl(lectId);
		
		if (vodUrl == null || "NO_URL".equals(vodUrl)) {
			return new BizVodApiResponse(BizApiResponseCode.NOT_FOUND_VOD_URL);
		}
		
		return new BizVodApiResponse(vodUrl);
		
	}

	@BizApiLog(bizApiResponseType=BizApiResponseType.VOD_URL, apiDsCd="VOD:ITEM", requestItem="itemId")
	@GetMapping("item/vod")
	public BizVodApiResponse itemVod(
			@RequestParam(value = "itemId", required = true) Integer itemId,
			@RequestParam(value = "apiKey", required = true) @Valid @ApiKeyValidate String apiKey) {
		
		String vodUrl = apiService.getItemVodUrl(itemId);
		
		if (vodUrl == null || "NO_URL".equals(vodUrl)) {
			return new BizVodApiResponse(BizApiResponseCode.NOT_FOUND_VOD_URL);
		}
		
		return new BizVodApiResponse(vodUrl);
		
	}
	
}
