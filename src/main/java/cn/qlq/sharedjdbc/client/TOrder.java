package cn.qlq.sharedjdbc.client;

import javax.persistence.Id;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
// 对应逻辑表名称
@TableName("t_order")
public class TOrder {

	@Id
	private Long id;

	private Long order_id;

	private Long user_id;

	private String description;

}
