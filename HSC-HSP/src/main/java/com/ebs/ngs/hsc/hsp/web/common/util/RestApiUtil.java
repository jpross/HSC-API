package com.ebs.ngs.hsc.hsp.web.common.util;

import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ebs.ngs.hsc.hsp.system.helper.AppModuleHelper;

@Component("restApiUtil")
@PropertySource("classpath:application.properties")
public class RestApiUtil{

    @Value("${currentServerType}")
    private String currentServerType;
    
    /*
    * Connection Pooling을 적용한 RestTemplate
    */
	@Bean(name="restTemplateClient")
	public static RestTemplate restClient() {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		/*
		* 타임아웃 설정
		*/
		httpRequestFactory.setConnectTimeout(5000);
		httpRequestFactory.setReadTimeout(5000);
		httpRequestFactory.setConnectionRequestTimeout(5000);
		HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(300).setMaxConnPerRoute(50).build();
		httpRequestFactory.setHttpClient(httpClient);
		       
		return new RestTemplate(httpRequestFactory);
	}
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> getCall(String url, Map<String, Object> inputMap) {
		HttpHeaders header = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(inputMap, header);
		ResponseEntity<Map> responseEntity = null;
		Map<String, Object> uMap = null;
		
		if("S".equals(currentServerType)) {
			System.out.println("-----------/HSC-HSP/src/main/java/com/ebs/ngs/hsc/hsp/web/common/util/RestApiUtil.java-------111");
			System.out.println(url);
			System.out.println(entity);
			System.out.println("-----------/HSC-HSP/src/main/java/com/ebs/ngs/hsc/hsp/web/common/util/RestApiUtil.java-------222");
		}
		
		try {
			//responseEntity = AppModuleHelper.getModule().getRestTemplate().exchange(url, HttpMethod.GET, entity, Map.class);
			responseEntity = restClient().exchange(url, HttpMethod.GET, entity, Map.class);
			
			uMap = responseEntity.getBody();
		}
		catch (Exception e) {
			System.out.println("restAPiUtil Exception :" +e);
		}
		
		if("S".equals(currentServerType)) {
			System.out.println("-----------/HSC-HSP/src/main/java/com/ebs/ngs/hsc/hsp/web/common/util/RestApiUtil.java-------333");
			System.out.println(uMap);
			System.out.println("-----------/HSC-HSP/src/main/java/com/ebs/ngs/hsc/hsp/web/common/util/RestApiUtil.java-------444");
		}
		
		return uMap;
	}

	public Map<String, Object> postCall(String url, Map<String, Object> inputMap) {
		return call(url, inputMap, HttpMethod.POST);
	}
	
	public Map<String, Object> putCall(String url, Map<String, Object> inputMap) {
		return call(url, inputMap, HttpMethod.PUT);
	}
	
	public Map<String, Object> deleteCall(String url, Map<String, Object> inputMap) {
		return call(url, inputMap, HttpMethod.DELETE);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> call(String url, Map<String, Object> inputMap, HttpMethod httpMethod) {
		HttpHeaders header = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(inputMap, header);

		ResponseEntity<Map> responseEntity = AppModuleHelper.getModule().getRestTemplate().exchange(url, httpMethod, entity, Map.class);
		return responseEntity.getBody();
	}
}
