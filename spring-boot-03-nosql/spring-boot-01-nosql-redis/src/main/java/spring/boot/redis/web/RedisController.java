package spring.boot.redis.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 操作Redis字符串和散列数据类型
     *
     * @return
     */
    @GetMapping("/testStringAndHash")
    @ResponseBody
    public Map<String, Object> testStringAndHash() {
        redisTemplate.opsForValue().set("key1", "value1");
        // 这里使用的是JDK的序列化器,因此Redis保存时,不是整数,不能进行运算
        redisTemplate.opsForValue().set("int_key", "1");

        // 使用运算
        stringRedisTemplate.opsForValue().set("int", "1");

        // 获取底层Jedis连接
        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        jedis.decr("int");

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("field1", "value1");
        hashMap.put("field2", "value2");
        // 存一个散列数据类型
        stringRedisTemplate.opsForHash().putAll("hash", hashMap);
        // 新增一个字段
        stringRedisTemplate.opsForHash().put("hash", "field3", "value3");
        // 绑定散列操作的key,这样可以连续对同一个散列数据类型进行操作
        BoundHashOperations<String, Object, Object> boundHashOps = stringRedisTemplate.boundHashOps("hash");
        // 删除两个字段
        boundHashOps.delete("field1", "field2");
        // 新增一个字段
        boundHashOps.put("field4", "value5");

        HashMap<String, Object> retMap = new HashMap<>();
        retMap.put("success", true);
        return retMap;
    }

    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> testList() {
        HashMap<String, Object> retMap = new HashMap<>();
        retMap.put("success", true);
        return retMap;
    }
}
