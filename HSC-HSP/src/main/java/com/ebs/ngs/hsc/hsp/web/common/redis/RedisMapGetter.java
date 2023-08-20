package com.ebs.ngs.hsc.hsp.web.common.redis;

import org.springframework.data.redis.core.RedisTemplate;

@FunctionalInterface
public interface RedisMapGetter<T> {

	public T get(RedisTemplate<String, Object> restTemplate, String key, String hashKey);
	
}
