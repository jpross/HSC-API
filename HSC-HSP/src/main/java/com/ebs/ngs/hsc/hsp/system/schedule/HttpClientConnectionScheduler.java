package com.ebs.ngs.hsc.hsp.system.schedule;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class HttpClientConnectionScheduler extends Thread {
	
	private static final int IDLE_TIMEOUT = 30 * 1000;
	
	private final PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientConnectionScheduler.class);
	
	@Override
	@Scheduled(fixedDelay = IDLE_TIMEOUT)
	public void run() {
		try {
			if (poolingHttpClientConnectionManager != null) {
				poolingHttpClientConnectionManager.closeExpiredConnections();
				poolingHttpClientConnectionManager.closeIdleConnections(IDLE_TIMEOUT, TimeUnit.MILLISECONDS);
				LOGGER.info("{}: 커넥션 종료. [{}]", Thread.currentThread().getName(), LocalDateTime.now());
			}
			else {
				LOGGER.info("httpClientConnectionManager is null");
			}	
		}
		catch (Exception e) {
			LOGGER.info("예외발생: {}", Thread.currentThread().getName());
		}
	}

}
