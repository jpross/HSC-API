package com.ebs.ngs.hsc.hsp.web.common.redis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RedisService {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

	private final RedisTemplate<String, Object> redisTemplate;
	
	public long getExpireTime(String key) {
		return redisTemplate.getExpire(key);
	}
	
	public boolean setExpireTime(String key, long expireTime) {
		return redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		return getData(key, null, (r, k) -> (T)r.opsForValue().get(k), null);
	}
	
	public <T> T get(String key, Supplier<T> data) {
		return get(key, data, -1);
	}
	
	public <T> T get(String key, Supplier<T> data, long expireTime) {
		RedisDataWithoutParameter<T> executor = new RedisDataWithoutParameter<>(data);
		return get(key, executor, expireTime);
	}
	
	public <T, PT> T get(String key, PT params, Function<PT, T> data) {
		return get(key, params, data, -1);
	}
	
	public <T, PT> T get(String key, PT params, Function<PT, T> data, long expireTime) {
		RedisDataWithParameter<T, PT> executor = new RedisDataWithParameter<>(params, data);
		return get(key, executor, expireTime);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String key, RedisData<T> data, long expireTime) {
		return getData(
				key, 
				data, 
				(r, k) -> (T)r.opsForValue().get(k), 
				(r, k, d) -> r.opsForValue().set(k, d),
				expireTime
			);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getValueToMap(String key, String hashKey) {
		return getData(key, hashKey, null, (r, k, hk) -> (T) r.opsForHash().get(key, hk), null);
	}
	
	public <T> T getValueToMap(String key, String hashKey, RedisData<T> data) {
		return getValueToMap(key, hashKey, data, -1L);
	}
	
	public <T> T getListValueToMap(String key, String hashKey, RedisData<T> data) {
		return getListValueToMap(key, hashKey, data, -1);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getListValueToMap(String key, String hashKey, RedisData<T> data, long expireTime) {
		T tempData = getValueToMap(key, hashKey, data, expireTime);
		return (T) Arrays.asList(tempData.toString().split(","));
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getValueToMap(String key, String hashKey, RedisData<T> data, long expireTime) {
		return getData(
				key,
				hashKey,
				data,
				(r, k, hk) -> (T) r.opsForHash().get(k, hk),
				(r, k, hk, d) -> r.opsForHash().put(k, hk, d),
				expireTime
			);
	}
	
	public <K, V> Map<K, V> getMap(String key) {
		return getData(
				key, 
				null, 
				(r, k) -> {
					HashOperations<String, K, V> ops = r.opsForHash();
					return ops.entries(key);
				}, 
				null
			);
	}
	
	public <K, V> Map<K, V> getMap(String key, Supplier<Map<K, V>> data) {
		RedisDataWithoutParameter<Map<K, V>> executor = new RedisDataWithoutParameter<>(data);
		return getMap(key, executor);
	}
	
	public <K, V, PT> Map<K, V> getMap(String key, PT params, Function<PT, Map<K, V>> data) {
		RedisDataWithParameter<Map<K, V>, PT> executor = new RedisDataWithParameter<>(params, data);
		return getMap(key, executor);
	}
	
	private <K, V> Map<K, V> getMap(String key, RedisData<Map<K, V>> data) {
		return getMap(key, data, -1L);
	}
	
	private <K, V> Map<K, V> getMap(String key, RedisData<Map<K, V>> data, long expireTime) {
		return getData(
				key, 
				data,
				(r, k) -> {
					HashOperations<String, K, V> ops = r.opsForHash();
					return ops.entries(key);
				}, 
				(r, k, d) -> r.opsForHash().putAll(k, d),
				expireTime
			);
	}
	
	@SuppressWarnings("unchecked")
	public <K, V> List<Map<K, V>> getListMap(String key) {
		return getData(
				key, 
				null, 
				(r, k) -> {
					Object ops = r.opsForList().range(k, 0, -1);
					return (List<Map<K, V>>)ops;
				}, 
				null
			);
	}
	
	public <K, V> List<Map<K, V>> getListMap(String key, Supplier<List<Map<K, V>>> data) {
		RedisDataWithoutParameter<List<Map<K, V>>> executor = new RedisDataWithoutParameter<>(data);
		return getListMap(key, executor, 0, -1);
	}
	
	public <K, V> List<Map<K, V>> getListMap(String key, Supplier<List<Map<K, V>>> data, long start, long end) {
		RedisDataWithoutParameter<List<Map<K, V>>> executor = new RedisDataWithoutParameter<>(data);
		return getListMap(key, executor, start, end);
	}
	
	public <K, V, PT> List<Map<K, V>> getListMap(String key, PT params, Function<PT, List<Map<K, V>>> data) {
		RedisDataWithParameter<List<Map<K, V>>, PT> executor = new RedisDataWithParameter<>(params, data);
		return getListMap(key, executor, 0, -1);
	}
	
	public <K, V, PT> List<Map<K, V>> getListMap(String key, PT params, Function<PT, List<Map<K, V>>> data, long start, long end) {
		RedisDataWithParameter<List<Map<K, V>>, PT> executor = new RedisDataWithParameter<>(params, data);
		return getListMap(key, executor, start, end);
	}
	
	@SuppressWarnings("unchecked")
	public <K, V> List<Map<K, V>> getListMap(String key, RedisData<List<Map<K, V>>> data, long start, long end) {
		return getData(
				key, 
				data,
				(r, k) -> {
					Object ops = r.opsForList().range(k, start, end);
					return (List<Map<K, V>>)ops;
				}, 
				(r, k, d) -> d.forEach(map -> r.opsForList().rightPush(k, map))
			);
	}
	
	private <T> T getData(String key, RedisData<T> data, RedisDataGetter<T> getter, RedisDataSetter<T> setter) {
		return getData(key, data, getter, setter, -1L);
	}
	
	private <T> T getData(String key, RedisData<T> data, RedisDataGetter<T> getter, RedisDataSetter<T> setter, long expireTime) {
		
		boolean isReadyRedisServer = true;
		T tempData = null;
		
		try {
			if (getter != null) {
				tempData = getter.get(redisTemplate, key);
				// Redis에 데이터가 있을 경우
				if (tempData != null) {
					logger.info("=====================================================================================");
					logger.info("redis cache 사용: " + key);
					logger.info("=====================================================================================");
					return tempData;
				}
			}
		}
		catch (Exception e) {
			isReadyRedisServer = false;
		}
		
		// Redis에 없을 경우
		if (data != null) {
			tempData = data.get();
		}

		// 데이터가 없거나
		// Redis Server에 이상이 있거나
		// setter가 설정되지 않았을 경우 값 return
		if (tempData == null || isReadyRedisServer == false || setter == null) {
			return tempData;
		}
		
		setter.set(redisTemplate, key, tempData);
		logger.info("=====================================================================================");
		logger.info("redis cache 생성: " + key);
		logger.info("=====================================================================================");
		if (expireTime > 0L) {
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
		}
		
		return tempData;
		
	}
	
	private <T> T getData(String key, String hashKey, RedisData<T> data, RedisMapGetter<T> getter, RedisMapSetter<T> setter) {
		return getData(key, hashKey, data, getter, setter, -1L);
	}
	
	@SuppressWarnings("unchecked")
	private <T> T getData(String key, String hashKey, RedisData<T> data, RedisMapGetter<T> getter, RedisMapSetter<T> setter, long expireTime) {
		
		// Redis Server와 접속 시 문제가 발생할 경우 DB로만 서비스 가능하도록 처리
		boolean isReadyRedisServer = true;
		T tempData = null;
		
		try {
			if (getter != null) {
				tempData = getter.get(redisTemplate, key, hashKey);
				// Redis에 데이터가 있을 경우
				if (tempData != null) {
					logger.info("=====================================================================================");
					logger.info(String.format("redis cache 사용: %s:%s", key, hashKey));
					logger.info("=====================================================================================");
					return tempData;
				}
			}
		}
		catch (Exception e) {
			// Redis Server 문제 발생
			isReadyRedisServer = false;
		}
		
		// Redis에 없을 경우
		if (data != null) {
			tempData = data.get();
		}


		// 데이터가 없거나
		// Redis Server에 이상이 있거나
		// setter가 설정되지 않았을 경우 값 return
		if (tempData == null || isReadyRedisServer == false || setter == null) {
			return tempData;
		}

		// Data Type이 List일 경우 String으로 변환 처리
		// Redis Value 최종 Type이 String 이므로 String Type만 처리 가능
		if (tempData instanceof ArrayList) {
			List<String> listTempData = (List<String>) tempData;
			String listToStringTempData = String.join(",", listTempData);
			tempData = (T) listToStringTempData;
		}
		setter.set(redisTemplate, key, hashKey, tempData);
		logger.info("=====================================================================================");
		logger.info(String.format("redis cache 생성: %s:%s", key, hashKey));
		logger.info("=====================================================================================");
		if (redisTemplate.getExpire(key) < 0L && expireTime > 0L) {
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
		}
		
		return tempData;
		
	}

}
