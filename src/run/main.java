package run;

import java.sql.*;
import java.util.Scanner;

import Constants.AppConstants;
import Util.DButil;
import view.Login1;

/**
 * 模块说明：主函数
 * 
 */

public class main {

	private static String url = AppConstants.JDBC_URL;
	private static String user = AppConstants.JDBC_USERNAME; // mysql登录名
	private static String pass = AppConstants.JDBC_PASSWORD;// mysql登录密码（写自己之前设置的）
	private static Connection con;//
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws Exception {

		// Class.forName(AppConstants.JDBC_DRIVER);
		con = DriverManager.getConnection(url, user, pass);
		new Login1();
		// System.out.println("登录成功！");
		DButil.getDBUtil().close();
	}
}