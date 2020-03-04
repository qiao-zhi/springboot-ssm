package cn.qlq.shiro.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.qlq.shiro.bean.Permission;

public interface PermissionMapper extends JpaRepository<Permission, Integer> {
	Permission findByName(String name);
}
