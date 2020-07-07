package cn.qlq.mybatisplus.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("mpUser")
public class MpUser {

	@TableId(type = IdType.INPUT)
	private Long id;
	private String name;
	private Integer age;
	private String email;
	@TableField("uniqueCode")
	private String uniqueCode;

	@TableLogic
	private Integer deleted;
}