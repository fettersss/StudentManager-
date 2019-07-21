package view;

import Constants.AppConstants;

import java.sql.*;
import java.util.*;
import dao.TeacherDAO;
import view.MainView;

public class Login1 {

	/**
	 * ����ʵ�ָ����û��ĵ�¼
	 */
	private static String username;// �û���¼ע�������
	private static String password;// �û�����
	private static String url = AppConstants.JDBC_URL;
	private static String user = AppConstants.JDBC_USERNAME; // mysql��¼��
	private static String pass = AppConstants.JDBC_PASSWORD;// mysql��¼���루д�Լ�֮ǰ���õģ�
	private static Connection con;//
	static Scanner input = new Scanner(System.in);

	public Login1() throws Exception {
		// �������ݿ���������������
		boolean notexit = true;
		try {
			System.out.println("\n");
			System.out.println("����MySql server �ɹ�!\n");
			while(notexit) {
				// Class.forName(AppConstants.JDBC_DRIVER);
				con = DriverManager.getConnection(url, user, pass);
				System.out.println("**********�û�����**********");
				System.out.println("\t��ѡ��\n\t1:ѧ����¼\n\t2:��ͨ��ʦ��¼\n\t3.������ʦ��¼\n");
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
					System.out.println("�����������������룡");
					break;
			    }
			
			}

		} catch (Exception e) {
			System.out.println("����Mysql server ʧ��!");
			System.out.println(e.toString());
		}
	}

	// �û���¼
	public static void denglu() throws SQLException {
		System.out.println("����������˺ţ�");
		username = input.next();
		System.out.println("������������룺");
		password = input.next();

		String sql = "select id,password from student where id=? and password=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, username);
		ptmt.setString(2, password);
		ResultSet rs = ptmt.executeQuery();
		// �ӵ�¼�û��������˺�����������ѯ�����ݿ�����Ƿ������ͬ���˺�����
		if (rs.next()) {
			System.out.println("ѧ����¼�ɹ���");
		} else {
			System.out.println("�������������\n�����µ�¼��");
			denglu();
		}
		System.out.println("�������Ƿ��޸����룿��YES/NO��");
		String change = input.next();
		if (change.equals("YES")) {
			System.out.println("��������Ҫ�޸ĵ����룺");
			String pa = input.next();
			String sql2 = "update student set password=? where id=? ";
			PreparedStatement ptmt2 = con.prepareStatement(sql2);
			ptmt2.setString(1, pa);
			ptmt2.setString(2, username);
			int a = ptmt2.executeUpdate();
			if (a == 1) {
				System.out.println("�޸ĳɹ���");
			}
			MainView.printUI1(username);
		} else
			MainView.printUI1(username);
	}

	// ��ͨ��ʦ�û���¼
	public static void denglu2() throws SQLException {
		System.out.println("����������˺ţ�");
		username = input.next();
		System.out.println("������������룺");
		password = input.next();

		String sql = "select tid,password from teacher where tid=? and password=? and tid like '100%'";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, username);
		ptmt.setString(2, password);
		ResultSet rs = ptmt.executeQuery();
		// �ӵ�¼�û��������˺�����������ѯ�����ݿ�����Ƿ������ͬ���˺�����
		if (rs.next()) {
			System.out.println("��ͨ��ʦ��¼�ɹ���");

		} else {
			System.out.println("�������������\n�����µ�¼��");
			denglu2();
		}
		System.out.println("�������Ƿ��޸����룿��YES/NO��");
		String change = input.next();
		if (change.equals("YES")) {
			System.out.println("��������Ҫ�޸ĵ����룺");
			String pa = input.next();
			String sql2 = "update teacher set password=? where tid=? ";
			PreparedStatement ptmt2 = con.prepareStatement(sql2);
			ptmt2.setString(1, pa);
			ptmt2.setString(2, username);
			int a = ptmt2.executeUpdate();
			if (a == 1) {
				System.out.println("�޸ĳɹ���");
			}
			MainView.printUI2(username);

		} else
			MainView.printUI2(username);
	}

	// ������ʦ�û���¼
	public static void denglu3() throws SQLException {
		System.out.println("����������˺ţ�");
		username = input.next();
		System.out.println("������������룺");
		password = input.next();

		String sql = "select tid,password from teacher where tid=? and password=? and tid like '110%'";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, username);
		ptmt.setString(2, password);
		ResultSet rs = ptmt.executeQuery();
		// �ӵ�¼�û��������˺�����������ѯ�����ݿ�����Ƿ������ͬ���˺�����
		if (rs.next()) {
			System.out.println("������ʦ��¼�ɹ���");

		} else {
			System.out.println("�������������\n�����µ�¼��");
			denglu3();
		}
		System.out.println("�������Ƿ��޸����룿��YES/NO��");
		String change = input.next();
		if (change.equals("YES")) {
			System.out.println("��������Ҫ�޸ĵ����룺");
			String pa = input.next();
			String sql2 = "update teacher set password=? where tid=? ";
			PreparedStatement ptmt2 = con.prepareStatement(sql2);
			ptmt2.setString(1, pa);
			ptmt2.setString(2, username);
			int a = ptmt2.executeUpdate();
			if (a == 1) {
				System.out.println("�޸ĳɹ���");
			}
			MainView.printUI3(username);
		} else
			MainView.printUI3(username);

	}
}