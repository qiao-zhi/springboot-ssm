package cn.qlq.mybatisplus.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Entity(name = "mpUser")
@Data
@TableName("mpUser")
public class MpUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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