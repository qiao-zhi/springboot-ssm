package cn.qlq.springData;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user2")
public class UserController2 {

	@Autowired
	private UserDao userDao;

	@RequestMapping("addUser")
	public String adduser() {
		for (int i = 0; i < 5; i++) {
			User2 user2 = new User2();
			user2.setAddress("add" + i);
			user2.setUsername("username" + i);

			userDao.save(user2);
		}

		return "success";
	}

	@RequestMapping("deleteUser")
	public String deleteUser() {
		// 批量删除
		// userDao.deleteAll();

		userDao.delete(1);
		return "success";
	}

	@RequestMapping("updateUser")
	public User2 updateUser() {
		User2 user = userDao.getOne(4);
		user.setAddress("修改后地址");

		User2 user2 = userDao.saveAndFlush(user);
		return user2;
	}

	@RequestMapping("getCount")
	public Long getCount() {
		// 根据条件查询
		// userDao.count(example);

		long count = userDao.count();
		return count;
	}

	@RequestMapping("exists")
	public boolean exists() {
		// 根据条件判断
		// userDao.exists(Example<S>);

		boolean exists = userDao.exists(5);
		return exists;
	}

	@RequestMapping("getUser")
	public User2 getUser() {
		User2 user = userDao.findOne(2);

		return user;
	}

	@RequestMapping("getUsers")
	public List<User2> getUsers() {
		// 查询所有
		// List<User2> findAll = userDao.findAll();

		// 根据ID集合查询
		// userDao.findAll(ids);

		// 根据条件查询:
		// Example example = Example.of(user);
		User2 user = new User2();
		user.setAddress("Add");
		user.setUsername("user");

		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())// 查询username包含修改user
				.withIgnorePaths("address");// 忽略address属性

		Example<User2> example = Example.of(user, matcher);
		List<User2> users = userDao.findAll(example);
		return users;
	}

	@RequestMapping("getUsersPage")
	public Page<User2> getUsersPage() {
		// 根据条件查询:
		User2 user = new User2();
		user.setAddress("Add");
		user.setUsername("user");

		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())// 查询username包含修改user
				.withIgnorePaths("address");// 忽略address属性
		// Example example = Example.of(user);
		Example<User2> example = Example.of(user, matcher);

		// 构造排序
		List<Sort.Order> orders = new ArrayList<Sort.Order>();
		orders.add(new Sort.Order(Sort.Direction.DESC, "id"));
		Sort sort = new Sort(orders);

		// 构造请求参数，页号从0开始。
		PageRequest pageRequest = new PageRequest(0, 2, sort);

		// 如果不带条件不传第一个参数即可
		Page<User2> findAll = userDao.findAll(example, pageRequest);

		return findAll;
	}

}
