package com.fuzamei.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author ylx
 *         Created by fuzamei on 2018/4/22.
 */
@Component
@Slf4j
public class RedisUtil {

    private final JedisPool jedisPool;

    @Autowired
    public RedisUtil(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    //=============================common============================

    /**
     * 指定缓存失效时间
     *
     * @param key      键
     * @param time     时间 long类型
     * @param timeUnit 时间单位
     * @return
     */
    public final boolean expire(String key, long time, TimeUnit timeUnit) {
        Jedis jedis = jedisPool.getResource();
        try {
            if (timeUnit.equals(TimeUnit.SECONDS)) {
                if (time > 0) {
                    jedis.expire(key, (int) time);
                    return true;
                }
            }
            if (timeUnit.equals(TimeUnit.MILLISECONDS)) {
                if (time > 0) {
                    jedis.pexpire(key, time);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            log.error("设置redis失效时间出现异常");
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.ttl(key);
        } catch (Exception e) {
            log.error("从redis获取失效时间出现异常");
            return 0L;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean exists(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.exists(key);
        } catch (Exception e) {
            log.error("redis判断是否有key值出现异常");
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 删除缓存 (批量的key值删除)
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void delete(String... key) {
        Jedis jedis = jedisPool.getResource();
        try {
            if (key != null && key.length > 0) {
                if (key.length == 1) {
                    jedis.del(key[0]);
                } else {
                    jedis.del(key);
                }
            }
        } catch (Exception e) {
            log.error("redis删除key值出现异常");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    //============================String=============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return key == null ? null : jedis.get(key);
        } catch (Exception e) {
            log.error("redis获取key对应的value值出现异常");
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            return true;
        } catch (Exception e) {
            log.error("redis插入key-value对出现异常");
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, String value, long time, TimeUnit timeUnit) {
        Jedis jedis = jedisPool.getResource();
        try {
            if (null != timeUnit) {
                if (time > 0) {
                    if (timeUnit.equals(TimeUnit.DAYS)) {
                        jedis.psetex(key, time * 24L * 3600 * 1000, value);
                    }
                    if (timeUnit.equals(TimeUnit.MILLISECONDS)) {
                        jedis.psetex(key, time, value);
                    }
                    if (timeUnit.equals(TimeUnit.SECONDS)) {
                        jedis.psetex(key, 1000L * time, value);
                    }
                    if (timeUnit.equals(TimeUnit.HOURS)) {
                        jedis.psetex(key, 1000L * 3600 * time, value);
                    }
                    if (timeUnit.equals(TimeUnit.MINUTES)) {
                        jedis.psetex(key, 1000L * 60 * time, value);
                    }
                    if (timeUnit.equals(TimeUnit.MICROSECONDS)) {
                        jedis.psetex(key, time / 1000, value);
                    }
                    if (timeUnit.equals(TimeUnit.NANOSECONDS)) {
                        jedis.psetex(key, time / 1000 * 1000, value);
                    }
                } else {
                    set(key, value);
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("redis插入key-value对出现异常");
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 自增1
     *
     * @param key
     * @return
     */
    public boolean incr(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            Long increment = jedis.incr(key);
            if (!increment.equals(1L)) {
                throw new RuntimeException();
            }
            return true;
        } catch (Exception e) {
            log.error("redis自增出现异常");
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 自增数量自定义
     *
     * @param key
     * @param value
     * @return
     */
    public boolean incrBy(String key, long value) {
        Jedis jedis = jedisPool.getResource();
        try {
            Long increment = jedis.incrBy(key, value);
            if (!increment.equals(value)) {
                throw new RuntimeException();
            }
            return true;
        } catch (Exception e) {
            log.error("redis自增出现异常");
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * 获取多个数值
     *
     * @param keys
     * @return
     */
    public List<String> mget(String... keys) {
        Jedis jedis = jedisPool.getResource();
        try {
            List<String> mget = jedis.mget(keys);
            return mget;
        } catch (Exception e) {
            return Collections.emptyList();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long zadd(String key, double score, String member) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zadd(key, score, member);
        } catch (Exception e) {
            log.error("redis添加zadd异常");
            return 0L;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long zcard(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zcard(key);
        } catch (Exception e) {
            log.error("redis获取zcard异常");
            return 0L;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<String> zrangeByScore(String key, double min, double max) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zrangeByScore(key, min, max);
        } catch (Exception e) {
            log.error("redis获取zrangeByScore异常");
            return Collections.emptySet();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<String> zrevrangeByScore(String key, double min, double max) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zrevrangeByScore(key, min, max);
        } catch (Exception e) {
            log.error("redis获取zrevrangeByScore异常");
            return Collections.emptySet();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<String> zrange(String key, long start, long end) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zrange(key, start, end);
        } catch (Exception e) {
            log.error("redis获取zrange异常");
            return Collections.emptySet();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<String> zrevrange(String key, long start, long end) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zrevrange(key, start, end);
        } catch (Exception e) {
            log.error("redis获取zrevrange异常");
            return Collections.emptySet();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zrangeByScore(key, min, max, offset, count);
        } catch (Exception e) {
            log.error("redis获取zrangeByScore异常");
            return Collections.emptySet();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<Tuple> zrangeByScoreWithScore(String key, double min, double max, int offset, int count) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        } catch (Exception e) {
            log.error("redis获取zrangeByScoreWithScore异常");
            return Collections.emptySet();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        } catch (Exception e) {
            log.error("redis获取zrevrangeByScore异常");
            return Collections.emptySet();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScore(String key, double max, double min, int offset, int count) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("redis获取zrevrangeByScoreWithScore异常");
            return Collections.emptySet();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long zcount(String key, double min, double max) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.zcount(key, min, max);
        } catch (Exception e) {
            log.error("redis获取zcount异常");
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    //============================for lock downside======================================

    /**
     * stxNx
     *
     * @return
     */
    public boolean setNx(String key, String value, long expire) {
        Jedis jedis = jedisPool.getResource();
        try {
            String lock = jedis.set(key, value, "NX", "PX", expire);
            if (lock != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("redis加锁异常");
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * stxNx
     *
     * @return
     */
    public boolean setNx(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            String lock = jedis.set(key, value);
            if (lock != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("redis加锁异常");
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long incrOne(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.incr(key);

        } catch (Exception e) {
            return 0;
        } finally {
            jedis.close();
        }
    }



    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key.getBytes(), serialize(value));
            return true;
        } catch (Exception e) {
            log.error("redis插入key-value对出现异常");
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }



    //反序列化
    public static Object unserizlize(byte[] byt){
        ObjectInputStream oii=null;
        ByteArrayInputStream bis=null;
        bis=new ByteArrayInputStream(byt);
        try {
            oii=new ObjectInputStream(bis);
            Object obj=oii.readObject();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                oii.close();
            } catch (IOException e) {
                e.printStackTrace();
                oii=null;
            }
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
                bis=null;
            }
        }
        return null;
    }

    //序列化
    public static byte [] serialize(Object obj){
        ObjectOutputStream obi=null;
        ByteArrayOutputStream bai=null;
        try {
            bai=new ByteArrayOutputStream();
            obi=new ObjectOutputStream(bai);
            obi.writeObject(obj);
            byte[] byt=bai.toByteArray();
            return byt;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bai.close();
            } catch (IOException e) {
                bai=null;
                e.printStackTrace();
            }
            try {
                obi.close();
            } catch (IOException e) {
                obi=null;
                e.printStackTrace();
            }
        }
        return null;
    }

}
