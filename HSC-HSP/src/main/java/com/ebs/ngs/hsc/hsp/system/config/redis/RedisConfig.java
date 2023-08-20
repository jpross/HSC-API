package com.ebs.ngs.hsc.hsp.system.config.redis;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

	@Value("${spring.redis.host}")
    private String host;
	
	@Value("${spring.redis.port}")
	private int port;

    @Autowired
    RedisClusterConfigurationProperties clusterConfigurationProperties;

	@Bean
	@Primary
	public LettuceConnectionFactory lettuceConnectionFactory() {
		

        // 클러스터 설정이 있을 경우에만 사용하도록 설정
    	if(this.clusterConfigurationProperties != null && this.clusterConfigurationProperties.getNodes() != null ) {
    		
	        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(); 
	        
	        System.out.println("redis cluster +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	        
	        clusterConfigurationProperties.getNodes().forEach(s -> { 
	        		String[] url = s.split(":");	        
	        		redisClusterConfiguration.clusterNode(url[0],Integer.parseInt(url[1]));
	    	        

	        });

	        System.out.println(clusterConfigurationProperties.getNodes().toString());
	        System.out.println("redis cluster +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	        return new LettuceConnectionFactory(redisClusterConfiguration);
    	} else {
    		// 현재는 개발용 redis 서버로 접속하도록 한다.
    		LettuceClientConfiguration lettuceClientConfiguration = 
    				LettuceClientConfiguration
    					.builder()
    					.commandTimeout(Duration.ofMinutes(1))
    					.shutdownTimeout(Duration.ZERO)
    					.build();

	        System.out.println("개발용 redis 단독서버 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

	        System.out.println(host + ":" + String.valueOf(port));
	        
	        System.out.println("개발용 redis 단독서버 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	        
    		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
    		return new LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration);
    	}
    }
	
	@Bean
	@Primary
	public RedisTemplate<String, Object> redisTemplate() {
			
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        
        redisTemplate.setConnectionFactory(this.lettuceConnectionFactory());
        
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer()); 
        redisTemplate.setHashValueSerializer(new StringRedisSerializer()); 
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        
        return redisTemplate; 
	}
	
}