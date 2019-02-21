package cn.qlq.mapper.user;

import cn.qlq.bean.user.User;
import cn.qlq.bean.user.UserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
	int countByExample(UserExample example);

	int deleteByExample(UserExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	List<User> selectByExample(UserExample example);

	User selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

	int updateByExample(@Param("record") User record, @Param("example") UserExample example);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
}