package com.ebs.ngs.hsc.hsp.web.api.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ebs.biz.api.common.exception.BizApiExceptionHandler;
import com.ebs.biz.api.common.response.BizVodApiResponse;

@RestControllerAdvice("com.ebs.ngs.hsc.hsp.web.api.application.controller")
public class ApiExceptionHandler extends BizApiExceptionHandler<BizVodApiResponse> {

	@Override
	protected BizVodApiResponse getBizApiResponse() {
		return new BizVodApiResponse();
	}

}
