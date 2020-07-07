package cn.qlq.shiro.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.qlq.shiro.bean.ShiroUser;
public interface ShiroUserMapper extends JpaRepository<ShiroUser, Integer> {
	ShiroUser findByUsername(String username);

	ShiroUser findByUsernameAndPassword(String username, String password);
}
