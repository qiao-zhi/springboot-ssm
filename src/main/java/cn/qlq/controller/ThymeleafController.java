package cn.qlq.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.qlq.bean.user.User;

/**
 * 返回Thymeleaf相关页面与Thymeleaf相关练习
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("th")
public class ThymeleafController {

	/**
	 * 会跳转到templates/freemarker/index.html
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/index")
	public String index(ModelMap map) {
		map.addAttribute("name", "testname");
		return "thymeleaf/index";
	}

	/**
	 * 会跳转到templates/thymeleaf/center/center.html
	 * 
	 * @return
	 */
	@RequestMapping("/center")
	public String center() {
		return "thymeleaf/center/center";
	}

	/**
	 * 会跳转到templates/thymeleaf/test.html
	 * 
	 * @return
	 */
	@RequestMapping("/test")
	public String test(ModelMap map) {
		User user = new User();
		user.setId(2);
		user.setSex("nv");
		user.setUsername("manager");
		user.setAddress("http://qiaoliqiang.cn");
		map.addAttribute("user", user);

		User user2 = user;
		List<User> userList = new ArrayList<>();
		userList.add(user);
		userList.add(user2);

		map.addAttribute("userList", userList);
		return "thymeleaf/test";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/postform")
	public String postform(User user) {
		System.out.println(user.getUsername());
		System.out.println(user.getAddress());
		return "redirect:/th/test";
	}
}
