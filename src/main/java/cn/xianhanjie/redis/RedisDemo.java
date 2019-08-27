package cn.xianhanjie.redis;

import java.util.HashMap;
import redis.clients.jedis.Jedis;

public class RedisDemo {

	String result;
	
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1");//主机IP
		jedis.auth("123456");//Redis服务密码
		System.out.println("连接测试："+jedis.ping());
		
		RedisDemo rd = new RedisDemo();
		rd.StringTest(jedis);
		rd.ListTest(jedis);
		rd.HashTest(jedis);
		rd.SetTest(jedis);
		rd.ZSetTest(jedis);
		
		jedis.close();
	}
	
	/**
	 * 测试Jedis对String类型的操作
	 * @param jedis	连接
	 */
	public void StringTest (Jedis jedis) {
		System.out.println("-----String类型测试-----");
		result = jedis.set("string:name", "i am string");
		System.out.println("String设置KV状态："+result);
		result = jedis.get("string:name");
		System.out.println("String获取KEY结果："+result);
	}
	
	/**
	 * 测试Jedis对Hash类型的操作
	 * @param jedis	连接
	 */
	public void HashTest (Jedis jedis) {
		System.out.println("-----Hash类型测试-----");
		HashMap<String,String> hm = new HashMap<String,String>();
		hm.put("name", "hash");
		hm.put("age", "22");
		hm.put("sex", "man");
		result = jedis.hset("hash:user", hm).toString();
		System.out.println("Hash设置KV状态："+result);
		result = jedis.hgetAll("hash:user").toString();
		System.out.println("Hash获取KEY结果："+result);
	}
	
	/**
	 * 测试Jedis对List类型的操作
	 * @param jedis	连接
	 */
	public void ListTest (Jedis jedis) {
		System.out.println("-----List类型测试-----");
		result = jedis.lpush("List", "iamlist").toString();
		System.out.println("List设置KV状态："+result);
		result = jedis.lpop("List");
		System.out.println("List获取KEY结果："+result);
	}
	
	
	/**
	 * 测试Jedis对Set类型的操作
	 * @param jedis	连接
	 */
	public void SetTest (Jedis jedis) {
		System.out.println("-----Set类型测试-----");
		result = jedis.sadd("Set", "member1","member2").toString();
		System.out.println("Set设置KV状态："+result);
		result = jedis.smembers("Set").toString();
		System.out.println("Set获取KEY结果："+result);
	}
	
	/**
	 * 测试Jedis对ZSet类型的操作
	 * @param jedis	连接
	 */
	public void ZSetTest (Jedis jedis) {
		System.out.println("-----ZSet类型测试-----");
		HashMap<String,Double>  hm  = new HashMap<String,Double>();
		hm.put("member1", 100.00);
		hm.put("member2", 66.00);
		hm.put("member3", 22.00);
		result = jedis.zadd("ZSet", hm).toString();
		System.out.println("ZSet设置KV状态："+result);
		result = jedis.zrangeByScore("ZSet",0.00, 100.00).toString();
		System.out.println("ZSet获取KEY结果："+result);
	}
	
}
