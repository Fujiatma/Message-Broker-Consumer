package com.tiket.messagebroker.consumer.springbootkafkaconsumer.config;

import java.lang.reflect.Method;

import javax.crypto.KeyGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	@Autowired
	private RedisConfigSerializer redisConfigSerializer; 
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jc = new JedisConnectionFactory();
		jc.setUsePool(true);
		jc.setHostName("localhost");
		jc.setPort(6379);
		return jc;
	}
	
	@Bean
	public RedisTemplate<String, RedisVO> redisTemplate(){
		RedisTemplate<String, RedisVO> rt = new RedisTemplate<>();
		rt.setConnectionFactory(jedisConnectionFactory());
		rt.setKeySerializer(new StringRedisSerializer());
		rt.setValueSerializer(redisConfigSerializer);
		return rt;
	}
	
	@Bean
	public RedisCacheManager redisCacheManager() throws InterruptedException {
		RedisCacheManager rcm = new RedisCacheManager(null, null, null, true);
		rcm.setTransactionAware(true);
		return rcm;
	}
	
	@Bean
	public org.springframework.cache.interceptor.KeyGenerator keyGenerator() {
		return new org.springframework.cache.interceptor.KeyGenerator() {
			
			@Override
			public Object generate(Object target, Method method, Object... params) {
				String key="";
				for(Object oc:params) {
					if(oc instanceof RedisVO) {
						RedisVO rv = (RedisVO) oc;
						key = String.valueOf(rv.getKeyID());
					}
					if(oc instanceof RedisVO) {
						key = String.valueOf(oc);
					}
				}
				return key;
			}
		};
	}
	
}
