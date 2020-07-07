package cn.qlq.mybatisplus.bean;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;

public class SelectByUniqueCode extends AbstractMethod {

	@Override
	public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
		String tableName = tableInfo.getTableName();
		String sql = "SELECT * FROM %s WHERE uniqueCode=#{uniqueCode}";
		SqlSource sqlSource = new RawSqlSource(configuration, String.format(sql, tableName), Object.class);
		return this.addSelectMappedStatementForTable(mapperClass, "selectByUniqueCode", sqlSource, tableInfo);
	}

}
