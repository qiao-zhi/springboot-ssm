package cn.qlq.mybatisplus.bean;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface MyBaseMapper<T> extends BaseMapper<T> {
	T selectByUniqueCode(String uniqueCode);
}