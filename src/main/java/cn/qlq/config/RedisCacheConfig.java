package cn.qlq.config;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import cn.qlq.utils.RedisTemplateUtils;

/**
 * Redis缓存整合配置
 * 
 * @author Administrator
 *
 */
@Configuration
// 声明开启缓存
@EnableCaching
public class RedisCacheConfig {
	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
		// 多个缓存的名称,目前只定义了一个(如果这里指定了缓存，后面的@Cacheable的value必须是这里的值)
		rcm.setCacheNames(Arrays.asList("usersCache", "logsCache"));
		// 设置缓存过期时间(秒)
		rcm.setDefaultExpiration(600);
		return rcm;
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate template = new RedisTemplate();
		template.setConnectionFactory(factory);

		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
		template.setKeySerializer(stringRedisSerializer);
		template.setValueSerializer(genericJackson2JsonRedisSerializer);
		template.setHashKeySerializer(stringRedisSerializer);
		template.setHashValueSerializer(genericJackson2JsonRedisSerializer);
		template.setEnableTransactionSupport(true);

		return template;
	}

	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object o, Method method, Object... params) {
				// 规定 本类名+方法名+参数名 为key
				StringBuilder sb = new StringBuilder();
				sb.append(o.getClass().getName());
				sb.append("-");
				sb.append(method.getName());
				sb.append("-");
				for (Object param : params) {
					sb.append(param.toString());
				}
				return sb.toString();
			}
		};
	}

	@Bean
	public RedisTemplateUtils redisTemplateUtils(RedisTemplate redisTemplate) {
		RedisTemplateUtils redisTemplateUtils = new RedisTemplateUtils();
		redisTemplateUtils.setRedisTemplate(redisTemplate);
		return redisTemplateUtils;
	}
}
