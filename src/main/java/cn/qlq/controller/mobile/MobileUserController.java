package cn.qlq.controller.mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.qlq.bean.user.User;
import cn.qlq.service.user.UserService;
import cn.qlq.utils.DefaultValue;
import cn.qlq.utils.JSONResultUtil;
import cn.qlq.utils.ValidateCheck;

@Controller /** 自动返回的是json格式数据 ***/
@RequestMapping("/mobile/user")
public class MobileUserController {

	private static final Logger logger = LoggerFactory.getLogger(MobileUserController.class);

	@Autowired
	private UserService userService;

	/**
	 * 添加user
	 * 
	 * @param user
	 *            springMVC自动映射的实体
	 * @return
	 */
	@RequestMapping("addUser")
	@ResponseBody
	public JSONResultUtil addUser(User user) {
		user.setCreatetime(new Date());
		logger.info("user -> {}", user);
		userService.addUser(user);
		return JSONResultUtil.ok();
	}

	@RequestMapping("test")
	@ResponseBody
	public String Test() {
		return "test";
	}

	/**
	 * 分页查询user
	 * 
	 * @param condition
	 * @return
	 */
	@RequestMapping("getUsers")
	@ResponseBody
	public String getUsers(@RequestParam Map condition) {
		int pageNum = 1;
		if (ValidateCheck.isNotNull(MapUtils.getString(condition, "pageNum"))) { // 如果不为空的话改变当前页号
			pageNum = Integer.parseInt(MapUtils.getString(condition, "pageNum"));
		}
		int pageSize = DefaultValue.PAGE_SIZE;
		if (ValidateCheck.isNotNull(MapUtils.getString(condition, "pageSize"))) { // 如果不为空的话改变当前页大小
			pageSize = Integer.parseInt(MapUtils.getString(condition, "pageSize"));
		}
		// 开始分页
		PageHelper.startPage(pageNum, pageSize);
		List<User> users = new ArrayList<User>();
		try {
			users = userService.getUsers(condition);
		} catch (Exception e) {
			logger.error("getUsers error！", e);
		}
		PageInfo<User> pageInfo = new PageInfo<User>(users);
		return JSONObject.toJSONString(pageInfo);
	}

	/**
	 * 删除user
	 * 
	 * @param user
	 *            springMVC自动映射的实体
	 * @return
	 */
	@RequestMapping("deleteUser")
	@ResponseBody
	public JSONResultUtil deleteUser(int id) {
		userService.deleteUser(id);
		return JSONResultUtil.ok();
	}

	/**
	 * 跳转打修改用户页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("updateUser")
	public String updateUser(int id, ModelMap map) {
		User user = userService.getUser(id);
		map.addAttribute("user", user);
		return "updateUser";
	}

	/**
	 * 添加user
	 * 
	 * @param user
	 *            springMVC自动映射的实体
	 * @return
	 */
	@RequestMapping("doUpdateUser")
	@ResponseBody
	public JSONResultUtil doUpdateUser(User user) {
		logger.info("user -> {}", user);
		userService.updateUser(user);
		return JSONResultUtil.ok();
	}
}