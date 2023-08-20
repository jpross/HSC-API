package com.ebs.ngs.hsc.hsp.web.common.util.api;

import com.ebs.ngs.core.api.response.ApiMessage;
import com.ebs.ngs.core.api.response.BaseResponse;

public class ApiResult<T, K> extends ApiMessage<T, K> {

	private static final long serialVersionUID = -7782689941534618874L;
	
	public ApiResult() {
	}
	
	public ApiResult(ApiMessage<T, K> apiMessage) {
		super.setApiHeader(apiMessage.getApiHeader());
		super.setResponse(apiMessage.getResponse());
		super.setData(apiMessage.getData());
		super.setBody(apiMessage.getBody());
	}
	
	public T getSingleData() {
		if (super.getData() == null || super.getData().size() == 0) {
			setResult(false, "not exists data");
			return null;
		}
		return super.getData().get(0);
	}
	
	public ApiResult<T, K> setResult(boolean result, String message) {
		BaseResponse response = super.getResponse();
		response.setResult(result);
		response.setMessage(message);
		return this;
	}
	
	public boolean isResult() {
		return super.getResponse().isResult();
	}
	
	public String getMessage() {
		return super.getResponse().getMessage();
	}

}
