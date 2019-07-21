package dao;

import java.sql.*;

import Constants.AppConstants;

/**
 * ģ��˵���� ѧ����ѯ������Ϣ�����пγ̳ɼ�
 * 
 */

public class StudentDAO {

	public static String url = AppConstants.JDBC_URL;
	public static String user = AppConstants.JDBC_USERNAME; // mysql��¼��
	public static String pass = AppConstants.JDBC_PASSWORD;// mysql��¼���루д�Լ�֮ǰ���õģ�
	public static Connection con;

	// ѧ������ѧ�Ų�ѯ������Ϣ
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
			System.out.println("\tѧ�ţ�" + id + "\n\t������" + name + "\n\t�Ա�" + sex + "\n\tԺϵ��" + dep + "\n\t��ϵ��ʽ��" + tel);
		}
		System.out.println("���ҳɹ���");
	}

	// ѧ������ѧ�Ų�ѯ���˿γ̳ɼ�
	public void gradeById(String ID) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "select * from grade where id=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, ID);
		ResultSet rs = ptmt.executeQuery();
		System.out.println("\t�γ�\t�ɼ�\n");
		while (rs.next()) {
			String cname = rs.getString(2);
			String cgrade = rs.getString(3);
			System.out.println("\t" + cname + "\t" + cgrade + "\n");
		}
		System.out.println("���ҳɹ���");
	}

}
