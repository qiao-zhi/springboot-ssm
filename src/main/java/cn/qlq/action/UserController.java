package cn.qlq.action;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.qlq.bean.User;
import cn.qlq.service.UserService;


@RestController/**自动返回的是json格式数据***/
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("list")
	public List<User> list(){
		List<User> list = userService.findAllUser();
		return list;
	}
}
