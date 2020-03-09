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
     * RedisTemplate中的序列化属性
     * 1)、DefaultSerializer     默认序列化器,缺省值为JdkSerializationRedisSerializer
     * 2)、KeySerializer         Redis键序列化器,缺省值为默认序列化器
     * 3)、ValueSerializer       Redis值序列化器,缺省值为默认序列化器
     * 4)、HashKeySerializer     Redis散列结构Field序列化器,缺省值为默认序列化器
     * 5)、HashValueSerializer   Redis散列结构value列化器,缺省值为默认序列化器
     * 6)、StringSerializer      字符串序列化器,RedisTemplate自动赋值为StringRedisSerializer
     * @return
     */
    @Bean
    public RedisTemplate<Object,Object> stringRedisTemplate() {
        RedisTemplate<Object, Object> objectObjectRedisTemplate = new RedisTemplate<>();
        RedisSerializer<String> stringSerializer = objectObjectRedisTemplate.getStringSerializer();
        //objectObjectRedisTemplate.setDefaultSerializer();
        objectObjectRedisTemplate.setKeySerializer(stringSerializer);
        objectObjectRedisTemplate.setHashKeySerializer(stringSerializer);
        return objectObjectRedisTemplate;
    }
}
