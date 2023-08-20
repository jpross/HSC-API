package com.ebs.ngs.hsc.hsp.web.common.util;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class RestTemplateFactory {
	
	private static RestTemplateFactory INSTANCE = new RestTemplateFactory();
	
	private RestTemplateFactory() {
	}
	
	public static RestTemplateFactory getInstance() {
		return INSTANCE;
	}
	
	public RestTemplate create() {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        HttpClient httpClient = HttpClientBuilder.create()
        		.setMaxConnTotal(150)
        		.setMaxConnPerRoute(50)
        		.build();
        httpRequestFactory.setHttpClient(httpClient);
        
        return new RestTemplate(httpRequestFactory);
	}

}
