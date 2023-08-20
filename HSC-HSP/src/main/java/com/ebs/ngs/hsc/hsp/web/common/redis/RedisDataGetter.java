package com.ebs.ngs.hsc.hsp.web.common.redis;

import org.springframework.data.redis.core.RedisTemplate;

@FunctionalInterface
public interface RedisDataGetter<T> {
	
	public T get(RedisTemplate<String, Object> redisTemplate, String key);

}
