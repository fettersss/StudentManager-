package run;

import java.sql.*;
import java.util.Scanner;

import Constants.AppConstants;
import Util.DButil;
import view.Login1;

/**
 * ģ��˵����������
 * 
 */

public class main {

	private static String url = AppConstants.JDBC_URL;
	private static String user = AppConstants.JDBC_USERNAME; // mysql��¼��
	private static String pass = AppConstants.JDBC_PASSWORD;// mysql��¼���루д�Լ�֮ǰ���õģ�
	private static Connection con;//
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws Exception {

		// Class.forName(AppConstants.JDBC_DRIVER);
		con = DriverManager.getConnection(url, user, pass);
		new Login1();
		// System.out.println("��¼�ɹ���");
		DButil.getDBUtil().close();
	}
}