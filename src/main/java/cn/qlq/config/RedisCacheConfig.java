package cn.qlq.config;

import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.context.annotation.RequestScope;

import cn.qlq.utils.RedisTemplateUtils;

@Configuration
// 声明开启缓存
@EnableCaching
public class RedisCacheConfig {

	@Bean
	public RedisTemplateUtils redisTemplateUtils(RedisTemplate redisTemplate) {
		RedisTemplateUtils redisTemplateUtils = new RedisTemplateUtils();
		redisTemplateUtils.setRedisTemplate(redisTemplate);
		return redisTemplateUtils;
	}

	@Bean
	@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
	public CacheManager cacheManager(@Autowired RedisConnectionFactory redisConnectionFactory) {
		// 生成随机时间
		int startHour = 24;
		int endHour = 48;
		int nextInt = RandomUtils.nextInt(startHour, endHour);
		long ttl = nextInt * 60 * 60 * 1000;

		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMillis(ttl));
		return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
				.cacheDefaults(redisCacheConfiguration).build();
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, String> template = new RedisTemplate<>();
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
}