package cn.xianhanjie.redis;

import java.util.HashMap;
import redis.clients.jedis.Jedis;

public class RedisDemo {

	String result;
	
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1");//����IP
		jedis.auth("123456");//Redis��������
		System.out.println("���Ӳ��ԣ�"+jedis.ping());
		
		RedisDemo rd = new RedisDemo();
		rd.StringTest(jedis);
		rd.ListTest(jedis);
		rd.HashTest(jedis);
		rd.SetTest(jedis);
		rd.ZSetTest(jedis);
		
		jedis.close();
	}
	
	/**
	 * ����Jedis��String���͵Ĳ���
	 * @param jedis	����
	 */
	public void StringTest (Jedis jedis) {
		System.out.println("-----String���Ͳ���-----");
		result = jedis.set("string:name", "i am string");
		System.out.println("String����KV״̬��"+result);
		result = jedis.get("string:name");
		System.out.println("String��ȡKEY�����"+result);
	}
	
	/**
	 * ����Jedis��Hash���͵Ĳ���
	 * @param jedis	����
	 */
	public void HashTest (Jedis jedis) {
		System.out.println("-----Hash���Ͳ���-----");
		HashMap<String,String> hm = new HashMap<String,String>();
		hm.put("name", "hash");
		hm.put("age", "22");
		hm.put("sex", "man");
		result = jedis.hset("hash:user", hm).toString();
		System.out.println("Hash����KV״̬��"+result);
		result = jedis.hgetAll("hash:user").toString();
		System.out.println("Hash��ȡKEY�����"+result);
	}
	
	/**
	 * ����Jedis��List���͵Ĳ���
	 * @param jedis	����
	 */
	public void ListTest (Jedis jedis) {
		System.out.println("-----List���Ͳ���-----");
		result = jedis.lpush("List", "iamlist").toString();
		System.out.println("List����KV״̬��"+result);
		result = jedis.lpop("List");
		System.out.println("List��ȡKEY�����"+result);
	}
	
	
	/**
	 * ����Jedis��Set���͵Ĳ���
	 * @param jedis	����
	 */
	public void SetTest (Jedis jedis) {
		System.out.println("-----Set���Ͳ���-----");
		result = jedis.sadd("Set", "member1","member2").toString();
		System.out.println("Set����KV״̬��"+result);
		result = jedis.smembers("Set").toString();
		System.out.println("Set��ȡKEY�����"+result);
	}
	
	/**
	 * ����Jedis��ZSet���͵Ĳ���
	 * @param jedis	����
	 */
	public void ZSetTest (Jedis jedis) {
		System.out.println("-----ZSet���Ͳ���-----");
		HashMap<String,Double>  hm  = new HashMap<String,Double>();
		hm.put("member1", 100.00);
		hm.put("member2", 66.00);
		hm.put("member3", 22.00);
		result = jedis.zadd("ZSet", hm).toString();
		System.out.println("ZSet����KV״̬��"+result);
		result = jedis.zrangeByScore("ZSet",0.00, 100.00).toString();
		System.out.println("ZSet��ȡKEY�����"+result);
	}
	
}
