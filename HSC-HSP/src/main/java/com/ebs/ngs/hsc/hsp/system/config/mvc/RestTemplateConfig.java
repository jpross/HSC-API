package com.ebs.ngs.hsc.hsp.system.config.mvc;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	private static final int CONNECTION_MAX_PER_ROUTE = 50;
	
	private static final int CONNECTION_MAX_TOTAL = 260;
	
	private static final int TIME_OUT = 20000; // 20초
	
	@Bean
	public RestTemplate restTemplate() {
		
		SocketConfig socketConfig = SocketConfig.custom()
				.setSoKeepAlive(true)
				.setSoReuseAddress(true)
				.setSoTimeout(TIME_OUT)
				.setTcpNoDelay(true)
				.setSoLinger(0)
				.build();
		
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(TIME_OUT)
				.setConnectTimeout(TIME_OUT)
				.setConnectionRequestTimeout(TIME_OUT)
				.build();
		
		HttpClient httpClient = HttpClientBuilder.create()
				.setConnectionManager(poolingHttpClientConnectionManager())
				.setDefaultSocketConfig(socketConfig)
				.setDefaultRequestConfig(requestConfig)
				.build();
		
		ClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		
		return new RestTemplate(clientHttpRequestFactory);
		
	}
	
	@Bean
	public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
		
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setDefaultMaxPerRoute(CONNECTION_MAX_PER_ROUTE);
		connectionManager.setMaxTotal(CONNECTION_MAX_TOTAL);
		
		return connectionManager;
		
	}

}
