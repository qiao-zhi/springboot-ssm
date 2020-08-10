package cn.qlq.config.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;

import com.zaxxer.hikari.HikariDataSource;

public class DatabaseUtils {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "123456";

	public synchronized static void addTargetDataSources(String database) {
		DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
		Map<Object, Object> dynamicTargetDataSources = DynamicDataSource.dynamicTargetDataSources;
		Set<Object> keySet = dynamicTargetDataSources.keySet();
		if (!keySet.contains(database)) {
			HikariDataSource druidDataSource = new HikariDataSource();
			druidDataSource.setUsername(DB_URL + database
					+ "?useUnicode=true&amp;characterEncoding=utf8&amp;autoReconnect=true&amp;autoReconnectForPools=true&amp;failOverReadOnly=false");
			druidDataSource.setUsername(USER);
			druidDataSource.setPassword(PASS);
			druidDataSource.setDriverClassName(JDBC_DRIVER);
			dynamicDataSource.addTargetDataSources(database, druidDataSource);
		}
	}

	public static void createDatabase(String database) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "CREATE DATABASE IF NOT EXISTS " + database
					+ " DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci";
			stmt.executeUpdate(sql);

			// 如果需要执行一些数据库初始化的脚本可以放到这里

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}
}
