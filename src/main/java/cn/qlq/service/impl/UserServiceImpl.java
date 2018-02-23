package cn.qlq.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qlq.bean.User;
import cn.qlq.mapper.UserMapper;
import cn.qlq.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	
	public List<User> findAllUser() {
		List<User> list = userMapper.findAll();
		return list;
	}


}
