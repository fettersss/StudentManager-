package view;

import Constants.AppConstants;

import java.sql.*;
import java.util.*;
import dao.TeacherDAO;
import view.MainView;

public class Login1 {

	/**
	 * 用以实现各类用户的登录
	 */
	private static String username;// 用户登录注册的姓名
	private static String password;// 用户密码
	private static String url = AppConstants.JDBC_URL;
	private static String user = AppConstants.JDBC_USERNAME; // mysql登录名
	private static String pass = AppConstants.JDBC_PASSWORD;// mysql登录密码（写自己之前设置的）
	private static Connection con;//
	static Scanner input = new Scanner(System.in);

	public Login1() throws Exception {
		// 加载数据库连接驱动并连接
		boolean notexit = true;
		try {
			System.out.println("\n");
			System.out.println("连接MySql server 成功!\n");
			while(notexit) {
				// Class.forName(AppConstants.JDBC_DRIVER);
				con = DriverManager.getConnection(url, user, pass);
				System.out.println("**********用户界面**********");
				System.out.println("\t请选择：\n\t1:学生登录\n\t2:普通教师登录\n\t3.教务处老师登录\n");
				System.out.println("****************************");

				int i = input.nextInt();
				switch (i) {
				case 1:
					denglu();
					notexit = false;
					break;
				case 2:
					denglu2();
					notexit = false;
					break;
				case 3:
					denglu3();
					notexit = false;
					break;
				default:
					System.out.println("输入有误，请重新输入！");
					break;
			    }
			
			}

		} catch (Exception e) {
			System.out.println("连接Mysql server 失败!");
			System.out.println(e.toString());
		}
	}

	// 用户登录
	public static void denglu() throws SQLException {
		System.out.println("请输入你的账号：");
		username = input.next();
		System.out.println("请输入你的密码：");
		password = input.next();

		String sql = "select id,password from student where id=? and password=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, username);
		ptmt.setString(2, password);
		ResultSet rs = ptmt.executeQuery();
		// 从登录用户给出的账号密码来检测查询在数据库表中是否存在相同的账号密码
		if (rs.next()) {
			System.out.println("学生登录成功！");
		} else {
			System.out.println("姓名或密码错误！\n请重新登录：");
			denglu();
		}
		System.out.println("请问您是否修改密码？（YES/NO）");
		String change = input.next();
		if (change.equals("YES")) {
			System.out.println("请输入你要修改的密码：");
			String pa = input.next();
			String sql2 = "update student set password=? where id=? ";
			PreparedStatement ptmt2 = con.prepareStatement(sql2);
			ptmt2.setString(1, pa);
			ptmt2.setString(2, username);
			int a = ptmt2.executeUpdate();
			if (a == 1) {
				System.out.println("修改成功！");
			}
			MainView.printUI1(username);
		} else
			MainView.printUI1(username);
	}

	// 普通教师用户登录
	public static void denglu2() throws SQLException {
		System.out.println("请输入你的账号：");
		username = input.next();
		System.out.println("请输入你的密码：");
		password = input.next();

		String sql = "select tid,password from teacher where tid=? and password=? and tid like '100%'";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, username);
		ptmt.setString(2, password);
		ResultSet rs = ptmt.executeQuery();
		// 从登录用户给出的账号密码来检测查询在数据库表中是否存在相同的账号密码
		if (rs.next()) {
			System.out.println("普通老师登录成功！");

		} else {
			System.out.println("姓名或密码错误！\n请重新登录：");
			denglu2();
		}
		System.out.println("请问您是否修改密码？（YES/NO）");
		String change = input.next();
		if (change.equals("YES")) {
			System.out.println("请输入你要修改的密码：");
			String pa = input.next();
			String sql2 = "update teacher set password=? where tid=? ";
			PreparedStatement ptmt2 = con.prepareStatement(sql2);
			ptmt2.setString(1, pa);
			ptmt2.setString(2, username);
			int a = ptmt2.executeUpdate();
			if (a == 1) {
				System.out.println("修改成功！");
			}
			MainView.printUI2(username);

		} else
			MainView.printUI2(username);
	}

	// 教务处老师用户登录
	public static void denglu3() throws SQLException {
		System.out.println("请输入你的账号：");
		username = input.next();
		System.out.println("请输入你的密码：");
		password = input.next();

		String sql = "select tid,password from teacher where tid=? and password=? and tid like '110%'";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, username);
		ptmt.setString(2, password);
		ResultSet rs = ptmt.executeQuery();
		// 从登录用户给出的账号密码来检测查询在数据库表中是否存在相同的账号密码
		if (rs.next()) {
			System.out.println("教务处老师登录成功！");

		} else {
			System.out.println("姓名或密码错误！\n请重新登录：");
			denglu3();
		}
		System.out.println("请问您是否修改密码？（YES/NO）");
		String change = input.next();
		if (change.equals("YES")) {
			System.out.println("请输入你要修改的密码：");
			String pa = input.next();
			String sql2 = "update teacher set password=? where tid=? ";
			PreparedStatement ptmt2 = con.prepareStatement(sql2);
			ptmt2.setString(1, pa);
			ptmt2.setString(2, username);
			int a = ptmt2.executeUpdate();
			if (a == 1) {
				System.out.println("修改成功！");
			}
			MainView.printUI3(username);
		} else
			MainView.printUI3(username);

	}
}