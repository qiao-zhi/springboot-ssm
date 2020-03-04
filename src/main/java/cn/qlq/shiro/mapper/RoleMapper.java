package cn.qlq.shiro.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.qlq.shiro.bean.Role;

public interface RoleMapper extends JpaRepository<Role, Integer> {
	Role findByName(String name);
}
