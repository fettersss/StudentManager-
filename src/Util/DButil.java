package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Constants.AppConstants;

/**
 * 模块说明：数据库工具类
 * 
 */
public class DButil {
	private static DButil db;

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	private DButil() {

	}

	public static DButil getDBUtil() {
		if (db == null) {
			db = new DButil();
		}
		return db;
	}

	public int executeUpdate(String sql) {
		int result = -1;
		if (getConn() == null) {
			return result;
		}
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int executeUpdate(String sql, Object[] obj) {
		int result = -1;
		if (getConn() == null) {
			return result;
		}
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
			result = ps.executeUpdate();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ResultSet executeQuery(String sql) {
		if (getConn() == null) {
			return null;
		}
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet executeQuery(String sql, Object[] obj) {
		if (getConn() == null) {
			return null;
		}
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public Connection getConn() {
		try {

			// Class.forName(AppConstants.JDBC_DRIVER);
			conn = DriverManager.getConnection(AppConstants.JDBC_URL, AppConstants.JDBC_USERNAME,
					AppConstants.JDBC_PASSWORD);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public boolean exeute(String sql) {
		if (getConn() == null) {
			return false;
		}
		try {
			Statement statement = conn.createStatement();
			statement.execute(sql);
			statement.close();
			return true;
		} catch (SQLException e) {
//			e.printStackTrace();
			return false;
		}
	}

	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
