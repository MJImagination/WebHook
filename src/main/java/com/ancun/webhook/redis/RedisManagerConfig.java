package com.ancun.webhook.redis;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

//@Configuration
public class RedisManagerConfig {

//	@Bean
//	public JedisConnectionFactory redisConnectionFactory() {
//		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
//
//		// Defaults
//		redisConnectionFactory.setHostName("127.0.0.1");
//		redisConnectionFactory.setPort(6379);
//		return redisConnectionFactory;
//	}
//
//
//	@Autowired
//	private JedisConnectionFactory jedisConnectionFactory;
//
//	@Bean
//	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
//		StringRedisTemplate template = new StringRedisTemplate(factory);
//		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//		ObjectMapper om = new ObjectMapper();
//		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//		jackson2JsonRedisSerializer.setObjectMapper(om);
//		template.setValueSerializer(jackson2JsonRedisSerializer);
//		template.afterPropertiesSet();
//		return template;
//	}
//
//	@Bean
//	public CacheManager cacheManager(RedisTemplate redisTemplate) {
//		RedisCacheManagerBuilder builder = RedisCacheManagerBuilder.fromConnectionFactory(jedisConnectionFactory);
//		Set<String> cacheNames = new HashSet<String>() {{
//			add("user");
//		}};
//		builder.initialCacheNames(cacheNames); //设置多个缓存
//		return builder.build();
//	}


}
