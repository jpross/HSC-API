package com.ebs.ngs.hsc.hsp.web.common.util.api;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ebs.ngs.core.api.response.ApiHeader;
import com.ebs.ngs.core.api.response.ApiMessage;
import com.ebs.ngs.core.util.ApiCallUtil;
import com.ebs.ngs.hsc.hsp.system.helper.AppModuleHelper;
import com.ebs.ngs.hsc.hsp.web.common.util.MessageUtil;
import com.ebs.ngs.hsc.hsp.web.common.util.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiHelper {
	
	private ObjectMapper mapper = null;
	
	private final ApiCallUtil apiCallUtil = AppModuleHelper.getModule().getApiCallUtil();
	
	private final HttpServletRequest request;
	
	private final HttpServletResponse response;
	
	private final static String SITE_CD = "HSC";
	
	private final String uniqueKey;
	
	private int callSequence = 0;
	
	private String url = null;
	
	@SuppressWarnings("unused")
	private String apiName = null;
	
	private Map<String, Object> data = null;
	
	private Object payLoad = null;
	
	private HttpMethod type = null;
	
	private String jwtToken = null;
	
	@SuppressWarnings({ "rawtypes" })
	private Consumer success;
	
	@SuppressWarnings({ "rawtypes" })
	private Consumer error;
	
	/**
	 * 필수 생성자
	 * @param request
	 * @param response
	 */
	public ApiHelper(HttpServletRequest request, HttpServletResponse response) {
		this(request, response, null, null, null, HttpMethod.GET, null, null);
	}

	/**
	 * 생성자
	 * @param request
	 * @param response
	 * @param url
	 * @param apiName
	 * @param data
	 * @param type
	 * @param success
	 * @param error
	 */
	@SuppressWarnings("rawtypes")
	public ApiHelper(HttpServletRequest request, HttpServletResponse response, String url, String apiName, Map<String, Object> data, HttpMethod type, Consumer success, Consumer error) {
		this.request = request;
		this.response = response;
		this.url = url;
		this.apiName = apiName;
		this.data = data;
		this.type = type;
		this.success = success;
		this.error = error;
		Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
		Integer timeHashKey = Long.valueOf(timestamp.getTime()).hashCode();
		uniqueKey = String.format("%s_%s_%d", SITE_CD, RandomStringUtils.randomAlphanumeric(10), timeHashKey);
	}
	
	/**
	 * API Url 설정
	 * @param url
	 * @return
	 */
	public ApiHelper setUrl(String url) {
		setUrl(url, false);
		return this;
	}
	
	/**
	 * API Url 설정
	 * @param url
	 * @param isSSL SSL 적용유무(기본 사용안 함)
	 * @return
	 */
	public ApiHelper setUrl(String url, boolean isSSL) {
		String tempUrl = url;
		this.url = isSSL ? tempUrl.replace("http:", "https:") : tempUrl;
		return this;
	}
	
	/**
	 * API Url 호출 시 URL이 아닌 Properties를 이용한 API 이름 설정
	 * @param apiName
	 * @return
	 */
	public ApiHelper setApiName(String apiName) {
		this.apiName = apiName;
		return this;
	}
	
	/**
	 * API Data 설정
	 * @param data
	 * @return
	 */
	public ApiHelper setData(Map<String, Object> data) {
		this.data = data;
		return this;
	}
	
	public ApiHelper addQuery(String key, Object value) {
		if (data == null) {
			data = new HashMap<>();
		}
		data.put(key, value);
		return this;
	}
	
	public ApiHelper setPayLoad(Object payLoad) {
		this.payLoad = payLoad;
		return this;
	}
	
	/**
	 * HTTP Method 설정
	 * @param type
	 * @return
	 */
	public ApiHelper setType(HttpMethod type) {
		this.type = type;
		return this;
	}
	
	/**
	 * API 통신 성공 시 처리
	 * @param success
	 * @return
	 */
	public <T> ApiHelper setSuccess(Consumer<T> success) {
		this.success = success;
		return this;
	}
	
	/**
	 * API 통신 실패 시 처리
	 * @param error
	 * @return
	 */
	public <T> ApiHelper setError(Consumer<T> error) {
		this.error = error;
		return this;
	}
	
	private ObjectMapper getMapper() {
		if (mapper == null) {
			mapper = new ObjectMapper();
		}
		return mapper;
	}
	
	/**
	 * API URL 셋팅 (Uri + Parameter)
	 * @return
	 */
	public String getUriToString() {
		
		UriComponents uri = null;
		
		if (data == null) {
			uri = UriComponentsBuilder.fromHttpUrl(url).build(false);
		}
		else {
			Map<String, String> tempData = new HashMap<>();
			
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			if (data != null) {
				data
				.entrySet()
				.forEach(entry -> {
					Object tempValue = entry.getValue();
					if (tempValue == null || String.class.isInstance(tempValue)) {
						tempData.put(entry.getKey(), String.valueOf(tempValue));
					}
				});
				params.setAll(tempData);
			}
			
			uri = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build(false);
		}
		
		return uri.toUriString();
				
	}
	
	/**
	 * Response Header 설정
	 * @param responseEntity
	 */
	private <T, K> void setResponseHeader(ResponseEntity<ApiMessage<T, K>> responseEntity) {
		try {
			ApiHeader apiHeader = responseEntity.getBody().getApiHeader();
			apiHeader.setFmySiteKey(uniqueKey);
			apiHeader.setCallSequence(++callSequence);
			setResponseHeader(apiCallUtil.getJsonString(apiHeader));
		}
		catch (JsonProcessingException e) {
			response.addHeader(ApiCallUtil.API_HEADER_KEY, e.getMessage());
		}
	}
	
	/**
	 * Response Header 설정
	 * @param body
	 */
	private void setResponseHeader(String apiHeader) {
		String tempApiHeader = apiHeader;
		tempApiHeader = tempApiHeader.replaceAll("[\r\n]", "");
		response.addHeader(ApiCallUtil.API_HEADER_KEY, tempApiHeader);
	}
	
	/**
	 * RequestEntity Generic Type 생성
	 * @return
	 */
	private RequestEntity<?> getRequestEntity() {
		
		RequestEntity<?> requestEntity = null;
		
		if (HttpMethod.GET.equals(type) || HttpMethod.DELETE.equals(type)) {
			requestEntity = apiCallUtil.apiRequestForUri(request, getUriToString() , type, null);
		}
		else {
			if (payLoad == null && data != null) {
				payLoad = data;
			}
			requestEntity = apiCallUtil.apiRequestForBody(request, getUriToString() , type, payLoad);
		}
		
		return requestEntity;
		
	}
	
	/**
	 * ResponseEntity Generic Type 생성
	 * @param responseType
	 * @param body
	 * @return
	 */
	private <T, K> ResponseEntity<ApiMessage<T, K>> exchange(Class<T> responseType, Class<K> body) {
		
		RequestEntity<?> requestEntity = getRequestEntity();

		ResponseEntity<ApiMessage<T, K>> responseEntity = AppModuleHelper.getModule().getRestTemplate().exchange(requestEntity, (new ParameterizedTypeReference<ApiMessage<T, K>>() {
			public Type getType() {
				return new GenericParameterizedTypeImpl((ParameterizedType)super.getType(), new Type[] { responseType, body });
			}
		}));
		
		return responseEntity;
		
	}
	
	/**
	 * ApiResult Type으로 API 응답 요청
	 * @param responseType
	 * @param body
	 * @return
	 */
	public <T, K> ApiResult<T, K> get(Class<T> responseType, Class<K> body) {
		
		ResponseEntity<ApiMessage<T, K>> responseEntity = exchange(responseType, body);
		setResponseHeader(responseEntity);
		
		return new ApiResult<T, K>(responseEntity.getBody());
		
	}
	
	/**
	 * ApiResult Type으로 API 응답 요청
	 * body 기본 String Type
	 * @param responseType
	 * @param body
	 * @return
	 */
	public <T> ApiResult<T, String> get(Class<T> responseType) {
		return get(responseType, String.class);
	}
	
	/**
	 * 리턴타입 없이 API 요청
	 * setSuccess와 setError 처리
	 * @param responseType
	 */
	@SuppressWarnings("unchecked")
	public <T> void call(Class<T> responseType) {

		ResponseEntity<ApiMessage<T, String>> responseEntity = exchange(responseType, String.class);
		
		if (responseEntity != null && responseEntity.getStatusCode().equals(HttpStatus.OK) && responseEntity.getBody() != null
				&& responseEntity.getBody().getResponse().getCode().equals(MessageUtil.getMessage("api-response.success.code"))) {
			if (success != null) {
				List<T> datas = responseEntity.getBody().getData();
				success.accept(datas.size() == 1 ? datas.get(0) : datas);
			}
		}
		else {
			if (error != null) {
				error.accept(responseEntity.getBody());
			}
		}
		
		setResponseHeader(responseEntity);
		
	}
	
	/**
	 * JSONObject로 API 응답 요청
	 * @return
	 */
	public JSONObject getJSON() {
		
		ApiResult<JSONObject, String> apiResult = get(JSONObject.class);
		
		return apiResult.getSingleData();
		
	}
	
	/**
	 * JSONArray로 API 응답 요청
	 * @return
	 */
	public JSONArray getJSONArray() {
		
		ApiResult<JSONArray, String> apiResult = get(JSONArray.class);
		
		return apiResult.getSingleData();
		
	}
	
	/**
	 * JSONObject의 결과를 String으로 API 응답 요청
	 * @return
	 */
	public String getString() {
		return getString(null);
	}
	
	/**
	 * JSONObject의 결과 중 특정 Key의 값을 String으로 API 응답 요청
	 * @return
	 */
	public String getString(String key) {
		
		ApiResult<JSONObject, String> apiResult = get(JSONObject.class);
		
		JSONObject jsonResult = apiResult.getData().get(0);
		
		if (StringUtil.isEmpty(key)) {
			return jsonResult.toJSONString();	
		}
		
		return jsonResult.get(key).toString();
		
	}
	
	public List<Map<String, Object>> getListMap() {
		return getListMap("data");
	}
	
	public List<Map<String, Object>> getListMap(String key) {
		
		List<Map<String, Object>> result = new ArrayList<>();
		
		ApiResult<JSONObject, String> apiResult = get(JSONObject.class);
		List<JSONObject> jsonList = apiResult.getData();
		
		jsonList.forEach(jsonResult -> {
			result.add(convertMap(jsonResult.toJSONString()));
		});
		
		return result;
		
	}
	
	/**
	 * JSONObject의 결과를 Map으로 API 응답 요청
	 * @return
	 */
	public Map<String, Object> getMap() {
		return getMap("data");
	}

	/**
	 * JSONObject의 결과 중 특정 Key의 값을 Map으로 API 응답 요청
	 * @param key
	 * @return
	 */
	public Map<String, Object> getMap(String key) {
		
		ApiResult<JSONObject, String> apiResult = get(JSONObject.class);
		JSONObject jsonResult = apiResult.getData().get(0);
		
		Map<String, Object> result = convertMap(jsonResult.toJSONString());
		
		return result;
		
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> convertMap(String jsonString) {
		return convertResult(jsonString, Map.class);
	}
	
	public <T> T convertResult(String jsonString, Class<T> type) {
		ObjectMapper mapper = getMapper();
		try {
			return (T) mapper.readValue(jsonString, type);
		}
		catch (JsonProcessingException e) {
			try {
				return type.newInstance();
			}
			catch (InstantiationException | IllegalAccessException e1) {
				return null;
			}
		}
	}
	
	public String getToken() {
		jwtToken = AppModuleHelper.getModule().getCookieUtil().getValue(request, AppModuleHelper.JWT_TOKEN_HEADER_PREFIX);
		return jwtToken == null ? StringUtil.EMPTY : jwtToken;
	}

}
