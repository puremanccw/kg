package com.charles.engine.redis;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisTest {
	
	private Jedis jedis;
	
	@Before
	public void setup() {
		jedis = new Jedis("192.168.188.128", 6379);
//		jedis.auth("admin");
	}
	
	@Test
	public void test1() {
		//-----添加数据----------  
		jedis.set("name","xinxin");//向key-->name中放入了value-->xinxin  
	 	System.out.println(jedis.get("name"));//执行结果：xinxin 
	 	System.out.println(jedis.get("test"));
	}
	
	
	public Jedis getJedis() {
		return jedis;
	}

	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}
	
	
}
