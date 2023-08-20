package com.ebs.ngs.hsc.hsp.system.config.redis;

import java.time.Duration;
// import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
// import redis.clients.jedis.JedisPoolConfig; 

import com.ebs.ngs.hsc.hsp.system.controller.ErrorCustomController;

@Configuration
public class RedisDataCacheConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(RedisDataCacheConfiguration.class);
	/*
	@Value("${spring.redis-session.host}")
    private String host;
	
	@Value("${spring.redis-session.port}")
	private int port;

    @Value("${spring.redis-session.cluster.nodes}")
    private List<String> sessionClusterNodes;
    */
    @Value("${spring.redis-data-cache.cluster.nodes}")
    private List<String> dataCacheClusterNodes;

    /**
     * session cluster 설정을 이용한 RedisConnectionFactory생성
     * @return
     */
    /*
    @Bean 
    public RedisConnectionFactory redisConnectionFactory() { 
    	RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(sessionClusterNodes);
    	logger.info("redisConnectionFactory:++++++++++++ " + sessionClusterNodes.toString());
    	return new LettuceConnectionFactory(redisClusterConfiguration);
    }
    */

    /**
     * session cluster 설정을 이용한 RedisConnectionFactory 생성
     * 
     * @return
     */
    /*
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
    	RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    	
    	redisTemplate.setConnectionFactory(this.redisConnectionFactory());
    	logger.info("redisTemplate:++++++++++++");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer()); 
        redisTemplate.setHashValueSerializer(new StringRedisSerializer()); 
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        
        return redisTemplate;
    }
    */
    /**
     * datacache cluster 설정을 이용한 RedisConnectionFactory생성
     * @return
     */
    @Bean(name="redisDataCacheConnectionFactory")
    public RedisConnectionFactory redisDataCacheConnectionFactory() { 
    	RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(dataCacheClusterNodes);

    	logger.info("redisConnectionFactory:++++++++++++");
    	logger.info(dataCacheClusterNodes.toString());
    	
    	return new LettuceConnectionFactory(redisClusterConfiguration);
    }

    /**
     * datacache cluster 설정을 이용한 RedisConnectionFactory 생성
     * 
     * @return
     */
    @Bean(name="redisDataCacheTemplate")
    public RedisTemplate<String, Object> redisDataCacheTemplate() {
    	RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    	
    	redisTemplate.setConnectionFactory(this.redisDataCacheConnectionFactory());
    	
        redisTemplate.setKeySerializer      (new StringRedisSerializer());
        redisTemplate.setHashKeySerializer  (new StringRedisSerializer()); 
        redisTemplate.setHashValueSerializer(new StringRedisSerializer()); 
        redisTemplate.setValueSerializer    (new StringRedisSerializer());

    	logger.info("redisDataCacheTemplate:++++++++++++");
   
        return redisTemplate;
    }
}