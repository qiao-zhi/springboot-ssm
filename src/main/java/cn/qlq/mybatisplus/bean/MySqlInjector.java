package cn.qlq.mybatisplus.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

@Component
public class MySqlInjector extends DefaultSqlInjector {

	/**
	 * 如果只需增加方法，保留MP自带方法 可以super.getMethodList() 再add
	 * 
	 * @return
	 */
	@Override
	public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
		List<AbstractMethod> methodList = super.getMethodList(mapperClass);
		methodList.add(new SelectByUniqueCode());
		return methodList;
	}
}
