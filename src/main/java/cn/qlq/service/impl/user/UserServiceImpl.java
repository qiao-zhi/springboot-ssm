package cn.qlq.service.impl.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.qlq.bean.user.User;
import cn.qlq.mapper.user.UserMapper;
import cn.qlq.service.user.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	public List<User> findAllUser() {
		List<User> list = userMapper.selectList(null);
		return list;
	}

	@Override
	public void addUser(User user) {
		userMapper.insert(user);
	}

	@Override
	public List<User> getUsers(Map condition) {
		return userMapper.selectList(null);
	}

	@Override
	public void deleteUser(int id) {
		userMapper.deleteById(id);
	}

	@Override
	public User getUser(int id) {
		return userMapper.selectById(id);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateById(user);
	}

	@Override
	public User findUserByUsername(String username) {
		return userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
	}

	@Override
	public User getUserByUserNameAndPassword(String username, String password) {
		return userMapper.selectOne(new QueryWrapper<User>().eq("username", username).eq("password", password));
	}

}
