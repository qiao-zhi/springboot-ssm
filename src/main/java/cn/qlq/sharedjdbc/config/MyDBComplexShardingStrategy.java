package cn.qlq.sharedjdbc.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

public class MyDBComplexShardingStrategy implements ComplexKeysShardingAlgorithm<Comparable<?>> {

	@Override
	public Collection<String> doSharding(Collection<String> availableTargetNames,
			ComplexKeysShardingValue<Comparable<?>> shardingValue) {

		System.out.println("=====MyDBComplexShardingStrategy=====");
		System.out.println(availableTargetNames);
		System.out.println(shardingValue);

		if (CollectionUtils.isEmpty(availableTargetNames)) {
			throw new RuntimeException("可用数据库为空");
		}

		List<String> result = new ArrayList<>();

		Map<String, Collection<Comparable<?>>> columnNameAndShardingValuesMap = shardingValue
				.getColumnNameAndShardingValuesMap();
		Set<String> keySet = columnNameAndShardingValuesMap.keySet();
		for (String key : keySet) {
			if (!"user_id".equals(key)) {
				continue;
			}

			Collection<Comparable<?>> collection = columnNameAndShardingValuesMap.get(key);
			Iterator<Comparable<?>> iterator = collection.iterator();
			while (iterator.hasNext()) {
				Integer next = Integer.valueOf(iterator.next().toString());
				Integer index = next % 2;
				String availableTargetName = IteratorUtils.get(availableTargetNames.iterator(), index);
				result.add(availableTargetName);
			}
		}

		System.out.println(result);

		return result;
	}

}