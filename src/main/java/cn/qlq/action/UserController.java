package cn.qlq.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;

import cn.qlq.bean.User;
import cn.qlq.service.UserService;

@RestController /** 自动返回的是json格式数据 ***/
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("list")
	public List<User> list() {
		// 只对紧邻的下一条select语句进行分页查询，对之后的select不起作用
		PageHelper.startPage(1, 3);
		List<User> list = userService.findAllUser();
		return list;
	}
}
