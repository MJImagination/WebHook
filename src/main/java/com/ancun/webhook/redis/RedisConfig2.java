package com.ancun.webhook.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

//@Configuration
public class RedisConfig2 {
//    public static  final Logger logger = LoggerFactory.getLogger(RedisConfig2.class);
//    @Bean
//    //不定义key时，采用；缓存key生成策略  类名+方法名+所有参数
//    public KeyGenerator keyGenerator() {
//        return new KeyGenerator() {
//            @Override
//            public Object generate(Object target, Method method, Object... params) {
//                StringBuilder sb = new StringBuilder();
//                sb.append(target.getClass().getName());
//                sb.append(method.getName());
//                for (Object obj : params) {
//                    sb.append(obj.toString());
//                }
//                logger.info("key = " + sb.toString());
//                return sb.toString();
//            }
//
//
////            public Object generate(Object target, Method method, Object... params) {
////                StringBuilder sb = new StringBuilder();
////                String[] value = new String[1];
////                // sb.append(target.getClass().getName());
////                // sb.append(":" + method.getName());
////                Cacheable cacheable = method.getAnnotation(Cacheable.class);
////                if (cacheable != null) {
////                    value = cacheable.value();
////                }
////                CachePut cachePut = method.getAnnotation(CachePut.class);
////                if (cachePut != null) {
////                    value = cachePut.value();
////                }
////                CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
////                if (cacheEvict != null) {
////                    value = cacheEvict.value();
////                }
////                sb.append(value[0]);
////                for (Object obj : params) {
////                    sb.append(":" + obj.toString());
////                }
////                logger.info("key = " + sb.toString());
////                return sb.toString();
////            }
////            public Object generate(Object target, Method method, Object... params) {
////                Object key=new BaseCacheKey(target,method,params);
////                return key.toString();
////            }
//        };
//    }
//
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory factory) {
//        RedisCacheManager rcm = RedisCacheManager.create(factory);
//        return rcm;
//    }
//
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
//        StringRedisTemplate template = new StringRedisTemplate(factory);
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setKeySerializer(jackson2JsonRedisSerializer);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        template.afterPropertiesSet();
//        return template;
//    }


}