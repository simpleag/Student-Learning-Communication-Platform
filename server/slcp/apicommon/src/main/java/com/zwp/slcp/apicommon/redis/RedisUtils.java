package com.zwp.slcp.apicommon.redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import org.apache.log4j.Logger;

/**
 * Created by ASUS on 2018/4/14.
 */
public class RedisUtils {
    //    @Value("${spring.redis.host}")
    private static String ADDR = "localhost";        // ip
    //    @Value("${spring.redis.port}")
    private static int PORT = 6379;       // 端口
    //    @Value("${spring.redis.password}")
    private static String AUTH = "";          // 密码(原始默认是没有密码)
    //    @Value("${spring.redis.password.max-active}")
    private static int MAX_ACTIVE = 100;       // 最大连接数
    //    @Value("${spring.redis.pool.max-idle}")
    private static int MAX_IDLE = 20;          // 设置最大空闲数
    //    @Value("${spring.redis.pool.max-wait}")
    private static int MAX_WAIT = -1;        // 最大连接时间
    //    @Value("${spring.redis.timeout}")
    private static int TIMEOUT = 100000;         // 超时时间
    private static boolean BORROW = true;         // 在borrow一个事例时是否提前进行validate操作

    private static JedisPool pool = null;
    private static Logger logger = Logger.getLogger(RedisUtils.class);

    /**
     * 初始化连接池
     */
    static {
        try {

            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(BORROW);
//            pool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH, 0);
            pool = new JedisPool(config, ADDR, PORT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setTestOnBorrow(BORROW);
        JedisPool jp = new JedisPool(config,"localhost",6379);
        System.out.println(jp.getResource());
    }

//    static {
//        try {
//            JedisPoolConfig config = new JedisPoolConfig();
//            // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
//            config.setBlockWhenExhausted(true);
//            // 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
//            config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
//            // 是否启用pool的jmx管理功能, 默认true
//            config.setJmxEnabled(true);
//            // 最大空闲连接数, 默认8个 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
//            config.setMaxIdle(8);
//            // 最大连接数, 默认8个
//            config.setMaxTotal(200);
//            // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
//            config.setMaxWaitMillis(1000 * 100);
//            // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
//            config.setTestOnBorrow(true);
////            pool = new JedisPool(config, ADDR, PORT, 3000, AUTH);
//            pool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH, 2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    /**
     * 获取连接
     */
    public static synchronized Jedis getJedis() {
        try {
            if (pool != null) {
                return pool.getResource();
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.info("redis连接池连接异常"+e);
            return null;
        }
    }

    /**
     * 向redis存入key和value,
     */

    public static boolean setString(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            String code = jedis.set(key, value);
            if (code.equals("ok")) {
                return true;
            }
        } catch (Exception e) {
            logger.debug("插入数据有异常.");
            return false;
        } finally {
            getColse(jedis);
        }
        return false;
    }

    /**
     * 向redis存入key和value,并设置过期时间
     */

    public static boolean setStrTime(String key, String value, int seconds) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            String code = jedis.set(key, value);
            System.out.println(code);
            jedis.expire(key, seconds);
            if (code.equals("OK")) {
                return true;
            }
        } catch (Exception e) {
            logger.debug("插入数据有异常."+ e);
            return false;
        } finally {
            getColse(jedis);
        }
        return false;
    }

    /**
     * 插入对象 并设置过期时间
     */
    public static boolean setObjTime(String key, Object obj, int seconds) {
        Jedis jedis = null;
        String value = JSONObject.toJSONString(obj);
        try {
            jedis = getJedis();
            String code = jedis.set(key, value);
            jedis.expire(key, seconds);
            if (code.equals("ok")) {
                return true;
            }
        } catch (Exception e) {
            logger.debug("redis插入数据有异常.");
            return false;
        } finally {
            getColse(jedis);
        }
        return false;
    }

    /**
     * 获取值
     */
    public static String getString(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = getJedis();
            value = jedis.get(key);
        } catch (Exception e) {
            logger.debug("redis获取值失败.");
        } finally {
            getColse(jedis);
        }
        return value;
    }

    /**
     * 设置失效时间
     */
    public static void expire(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.expire(key, seconds);

        } catch (Exception e) {
            logger.debug("redis设置时间失败.");
        } finally {
            getColse(jedis);
        }
    }


    /**
     * 删除
     */
    public static boolean del(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            Long code = jedis.del(key);
            if (code > 1) {
                return true;
            }
        } catch (Exception e) {
            logger.debug("删除key异常.");
            return false;
        } finally {
            getColse(jedis);
        }
        return false;
    }

    /**
     * @Description: 关闭连接
     */

    public static void getColse(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
