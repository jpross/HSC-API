package com.ebs.ngs.hsc.hsp.system.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import com.ebs.ngs.hsc.hsp.web.common.redis.RedisService;

@Configuration
public class ApiRedisConfig {
	
	@Autowired @Qualifier("redisDataCacheTemplate")
    private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private RedisService redisService;
	
	@Bean("apiRedisService")
	public RedisService aiRedisService() {
		redisService.setRedisTemplate(this.redisTemplate);
		return redisService;
	}

}
