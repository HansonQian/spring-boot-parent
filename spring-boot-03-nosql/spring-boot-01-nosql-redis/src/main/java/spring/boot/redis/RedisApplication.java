package spring.boot.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisApplication {
    public static void main(String[] args) {

        /*
        //Spring 集成Redis
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanConfiguration.class);
        context.refresh();
        RedisTemplate redisTemplate = context.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForHash().put("hash", "filed", "HValue");
        */

        SpringApplication.run(RedisApplication.class, args);
    }
}
