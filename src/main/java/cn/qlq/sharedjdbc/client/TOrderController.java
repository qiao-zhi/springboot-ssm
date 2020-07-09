package cn.qlq.sharedjdbc.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.qlq.utils.SnowflakeIdWorker;

@RestController
@RequestMapping("tOrder")
public class TOrderController {

	private Map<String, Object> result = new HashMap<>();

	@Autowired
	private TOrderMapper tOrderMapper;

	@RequestMapping("testAdd")
	public Map<String, Object> testAdd() {

		for (int i = 0; i < 20; i++) {
			TOrder tOrder = new TOrder();
			tOrder.setId(new SnowflakeIdWorker(0, 0).nextId());
			tOrder.setOrder_id(RandomUtils.nextLong(0, 5000000));
			tOrder.setUser_id(RandomUtils.nextLong(0, 5000000));
			tOrder.setDescription("description " + i);
			tOrderMapper.insert(tOrder);
		}

		result.put("success", true);
		return result;
	}

	@RequestMapping("list")
	public Map<String, Object> list() {
		List<TOrder> selectList = tOrderMapper.selectList(null);

		result.put("data", selectList);
		return result;
	}

}
