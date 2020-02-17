package spring.boot.redis.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;


/**
 * spring-data-redis数据类型封装操作接口,对应Redis的7种数据类型
 * 1)、GeoOperations             地理位置操作接口
 *      redisTemplate.opsForGeo();
 * 2)、HashOperations            散列操作接口
 *      redisTemplate.opsForHash();
 * 3)、HyperLogLogOperations     基数操作接口
 *      redisTemplate.opsFoHyperLoglog();
 * 4)、ListOperations            列表(链表)操作接口
 *      redisTemplate.opsForList();
 * 5)、SetOperations             集合操作接口
 *      redisTemplate.opsForSet();
 * 6)、ValueOperations           字符串操作接口
 *      redisTemplate.opsForValue();
 * 7)、ZSetOperations            有序集合操作接口
 *      redisTemplate.opsForZSet();
 *
 */
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
        Jedis jedis = (Jedis) Objects.requireNonNull(stringRedisTemplate.getConnectionFactory()).
                getConnection().getNativeConnection();
        // 减1操作，redisTempate不支持
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

    /**
     * redis中列表是一种链表结构
     * 查询性能低，新增和删除节点性能高
     * 在redis中存在从左到右或者从右到左的操作
     *
     * @return map
     */
    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> testList() {
        // 链表从左到右的顺序v10、v8、v6、v4、v2
        stringRedisTemplate.opsForList().leftPushAll("list1", "v2", "v4", "v6", "v8", "v10");
        // 链表从左到右的顺序v1、v2、v3、v4、v5、v6
        stringRedisTemplate.opsForList().rightPushAll("list2", "v1", "v2", "v3", "v4", "v5", "v6");
        // 绑定list2操作
        BoundListOperations<String, String> boundListOperations = stringRedisTemplate.boundListOps("list2");
        // 从右边弹出第一个成员
        String rightPop = boundListOperations.rightPop();
        // 从左边插入一个成员
        boundListOperations.leftPush("v0");
        // 链表长度
        Long size = boundListOperations.size();
        // 求链表下标区间成员,整个链表的下标区间为0到size-1,这里无法取出最后一个元素所以为size-2
        List<String> stringList = boundListOperations.range(0, size - 2);
        HashMap<String, Object> retMap = new HashMap<>();
        retMap.put("success", true);
        return retMap;
    }

    /**
     * redis对于集合是不允许成员重复的
     * 其数据结构是一种散列表的结构，所以它是无序的
     * 对与两个或者两个以上的集合，redis提供了交集、并集和差集的运算
     *
     * @return map
     */
    @GetMapping("/set")
    @ResponseBody
    public Map<String, Object> testSet() {
        stringRedisTemplate.opsForSet().add("set1", "v1", "v1", "v2", "v3", "v4", "v5");
        // 绑定set1操作
        BoundSetOperations<String, String> boundSetOperations = stringRedisTemplate.boundSetOps("set1");
        // 新增两个元素
        boundSetOperations.add("v6", "v7");
        // 删除两个元素
        boundSetOperations.remove("v1", "v7");
        // 返回所有元素
        Set<String> stringSet = boundSetOperations.members();
        // 求成员数
        Assert.notNull(stringSet, "不为空");
        Integer size = stringSet.size();

        // 求交集
        Set<String> intersect = boundSetOperations.intersect("set2");

        // 求交集，并且用新的集合diff保存
        boundSetOperations.intersectAndStore("set2", "intersect");

        // 求差集
        Set<String> diff = boundSetOperations.diff("set2");

        // 求差集，并且用新的集合diff保存
        boundSetOperations.diffAndStore("set2", "diff");

        // 求并集
        Set<String> union = boundSetOperations.union("set2");

        // 求并集，并且使用新集合
        boundSetOperations.unionAndStore("set2", "union");

        HashMap<String, Object> retMap = new HashMap<>();
        retMap.put("success", true);
        return retMap;
    }


    @GetMapping("/zset")
    @ResponseBody
    public Map<String, Object> testZset() {

        Set<ZSetOperations.TypedTuple<String>> typedTupleSet = new HashSet<>();

        for (int i = 0; i <= 9; i++) {
            // 分数
            double score = i * 0.1;
            // 创建一个TypedTuple对象,存入值和分数
            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<>("value" + i, score);
            typedTupleSet.add(typedTuple);
        }
        // 往有序集合插入元素
        stringRedisTemplate.opsForZSet().add("zset1",typedTupleSet);
        // 绑定zset1有序几个操作
        BoundZSetOperations<String, String> boundZSetOperations = stringRedisTemplate.boundZSetOps("zset1");
        // 增加一个元素
        boundZSetOperations.add("value10",0.26);
        Set<String> setRange = boundZSetOperations.range(1, 6);
        // 按分数排序获取有序集合
        Set<String> rangeByScore = boundZSetOperations.rangeByScore(0.2, 0.6);
        // 定义值范围
        //Range range = new Range();

        Map<String, Object> retMap = new HashMap<>();
        retMap.put("success", true);
        return retMap;
    }

}
