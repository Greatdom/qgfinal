package com.example.Service;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ProductServiceTest {
    @Test
    public void test() {
        JedisPool jedisPool = new JedisPool(); //创建一个连接池
        Jedis jedis = jedisPool.getResource(); //从连接池获取
        String ping = jedis.ping(); //测试连接
        System.out.println(ping); //如果成功连接上了Redis服务，此处将会输出PONG
        String token = jedis.get("username");//从Redis获取key为token的字符串
        System.out.println("token = " + token);//此处打印可以看到我们存入的字符串
        jedis.close(); //归还连接
    }
}
