package spring.boot.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Spring Boot集成Redis
 */
@Configuration
public class BeanConfiguration {
    /**
     * @return
     */
    @Bean
    public RedisTemplate stringRedisTemplate() {
        RedisTemplate<Object, Object> objectObjectRedisTemplate = new RedisTemplate<>();
        RedisSerializer<String> stringSerializer = objectObjectRedisTemplate.getStringSerializer();
        objectObjectRedisTemplate.setKeySerializer(stringSerializer);
        objectObjectRedisTemplate.setHashKeySerializer(stringSerializer);
        return objectObjectRedisTemplate;
    }
}
