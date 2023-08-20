package com.ebs.ngs.hsc.hsp.web.common.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.ebs.ngs.core.api.response.ApiMessage;
import com.ebs.ngs.core.api.service.ApiBaseResponseService;
import com.ebs.ngs.hsc.hsp.web.common.util.api.ApiResult;

@Component
public class ApiResponseService extends ApiBaseResponseService {
	
	public <T, K> ApiResult<T, K> getApiResult(HttpServletResponse response, HttpServletRequest request, T data) {
    	
    	List<T> datas = new ArrayList<>();
    	datas.add(data);
    	
		@SuppressWarnings("unchecked")
		ApiMessage<T, K> apiMessage = super.getResult(response, request, datas);
    	
    	return new ApiResult<T, K>(apiMessage);
		
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T, K> ApiResult<T, K> getApiResult(HttpServletResponse response, HttpServletRequest request, List<T> datas) {
    	
		ApiMessage apiMessage = super.getResult(response, request, datas);
    	return new ApiResult<T, K>(apiMessage);
		
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T, K> ApiResult<T, K> getApiResult(HttpServletResponse response, HttpServletRequest request, String code, String message) {
    	
		ApiMessage apiMessage = super.getApiReponseMessage(response, request, code, message);
		apiMessage.setData(new ArrayList<>());
    	return new ApiResult<T, K>(apiMessage);
		
    }

}
