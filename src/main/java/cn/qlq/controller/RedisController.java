package cn.qlq.controller;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.qlq.bean.user.User;
import cn.qlq.utils.JSONResultUtil;
import cn.qlq.utils.RedisTemplateUtils;
import cn.qlq.utils.RedisUtils;

@RestController
@RequestMapping("redis")
public class RedisController {
	@Autowired
	private StringRedisTemplate strRedis;

	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private RedisTemplateUtils redisTemplateUtils;

	@RequestMapping("/set")
	public JSONResultUtil test() {
		strRedis.opsForValue().set("mycache", "我存入的第一个cache", 660, TimeUnit.SECONDS);
		return JSONResultUtil.ok();
	}

	@RequestMapping("/setUser")
	public JSONResultUtil setUser() {
		User user = new User();
		user.setAddress("地址");
		user.setCreatetime(new Date());
		strRedis.opsForValue().set("user", JSONObject.toJSONString(user));
		return JSONResultUtil.ok();
	}

	@RequestMapping("/getUser")
	public JSONResultUtil getUser() {
		String string = strRedis.opsForValue().get("user");
		User user = JSONObject.parseObject(string, User.class);
		System.out.println(user);
		return JSONResultUtil.ok();
	}

	@RequestMapping("/getUserByUtils")
	public JSONResultUtil getUserByUtils() {
		String string = (String) redisUtils.get("user");
		User user = JSONObject.parseObject(string, User.class);
		System.out.println(user);
		return JSONResultUtil.ok();
	}

	@RequestMapping("/setUserByUtils2")
	public JSONResultUtil setUserByUtils2() {
		redisTemplateUtils.set("mm", "mm");
		return JSONResultUtil.ok();
	}

	@RequestMapping("/getUserByUtils2")
	public JSONResultUtil getUserByUtils2() {
		String string = (String) redisTemplateUtils.get("mm");
		System.out.println(string);
		return JSONResultUtil.ok();
	}
}
