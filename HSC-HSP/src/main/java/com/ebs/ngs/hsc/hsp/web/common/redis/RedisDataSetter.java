package com.ebs.ngs.hsc.hsp.web.common.redis;

import org.springframework.data.redis.core.RedisTemplate;

@FunctionalInterface
public interface RedisDataSetter<T> {

	public void set(RedisTemplate<String, Object> restTemplate, String key, T data);
	
}
