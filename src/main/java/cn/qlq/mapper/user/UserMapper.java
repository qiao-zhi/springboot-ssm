package cn.qlq.mapper.user;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.qlq.bean.user.User;

@Mapper
public interface UserMapper extends BaseMapper<User>{

}