package dao;

import java.sql.*;

import Constants.AppConstants;

/**
 * 模块说明： 学生查询本人信息及所有课程成绩
 * 
 */

public class StudentDAO {

	public static String url = AppConstants.JDBC_URL;
	public static String user = AppConstants.JDBC_USERNAME; // mysql登录名
	public static String pass = AppConstants.JDBC_PASSWORD;// mysql登录密码（写自己之前设置的）
	public static Connection con;

	// 学生根据学号查询本人信息
	public void queryById(String ID) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "select * from student where id=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, ID);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			String id = rs.getString(1);
			String name = rs.getString(2);
			String sex = rs.getString(3);
			String dep = rs.getString(4);
			String tel = rs.getString(5);
			System.out.println("\t学号：" + id + "\n\t姓名：" + name + "\n\t性别：" + sex + "\n\t院系：" + dep + "\n\t联系方式：" + tel);
		}
		System.out.println("查找成功！");
	}

	// 学生根据学号查询本人课程成绩
	public void gradeById(String ID) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "select * from grade where id=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, ID);
		ResultSet rs = ptmt.executeQuery();
		System.out.println("\t课程\t成绩\n");
		while (rs.next()) {
			String cname = rs.getString(2);
			String cgrade = rs.getString(3);
			System.out.println("\t" + cname + "\t" + cgrade + "\n");
		}
		System.out.println("查找成功！");
	}

}
