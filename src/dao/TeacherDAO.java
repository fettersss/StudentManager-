package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import Constants.AppConstants;
import Log.MyLog;
import Log.Loggable;

/**
 * ģ��˵���� ��ͨ��ʦ���幦�ܵ�ʵ��
 * 
 */

public class TeacherDAO {
	private static TeacherDAO ad = null;

	public static String url = AppConstants.JDBC_URL;
	public static String user = AppConstants.JDBC_USERNAME; // mysql��¼��
	public static String pass = AppConstants.JDBC_PASSWORD;// mysql��¼���루д�Լ�֮ǰ���õģ�
	public static Connection con;

	MyLog mylog = new MyLog();
	static Scanner input = new Scanner(System.in);

	public static synchronized TeacherDAO getInstance() {
		if (ad == null) {
			ad = new TeacherDAO();
		}
		return ad;
	}

	// ��ͨ��ʦ�鿴�������пγ���Ϣ
	public void queryForLessson(String id) throws SQLException {

		con = DriverManager.getConnection(url, user, pass);

		String sql = "select * from course where tid=?";
		PreparedStatement ptmt3 = con.prepareStatement(sql);
		ptmt3.setString(1, id);
		ResultSet rs1 = ptmt3.executeQuery();
		while (rs1.next()) {
			String name = rs1.getString(2);
			String gender = rs1.getString(3);
			System.out.println("\t�γ�����" + name + "\tѧ�֣�" + gender+"\n");
		}
		System.out.println("���ҳɹ���");

		mylog.addLog(Loggable.TYPE.INFORMATION, id + "���ŵ���ʦ�鿴���Լ������пγ���Ϣ\n");
	}

	public void studentGrade() throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		boolean notExit = true;
		while (notExit) {
			System.out.println("**********ѧ���ɼ�����**********");
			System.out.println("\t��ѡ��\n \t1:��ӳɼ�\n \t2:�޸ĳɼ�\n \t3:ɾ���ɼ�\n \t4:�鿴�ɼ�\n \t5:�˳�\n");
			System.out.println("********************************");
			int i = input.nextInt();
			switch (i) {
			case 1:
				System.out.println("������Ҫ��ӳɼ���ѧ����ѧ�š��γ������ɼ���");
				Scanner read = new Scanner(System.in);
				String in = read.nextLine();
				String[] temp = in.split("\\s");
				String id = temp[0];
				String cname = temp[1];
				String grade = temp[2];
				add(id, cname, grade);
				break;
			case 2:
				System.out.println("������Ҫ�޸ĳɼ���ѧ����ѧ�š��γ������ɼ���");
				Scanner read2 = new Scanner(System.in);
				String in2 = read2.nextLine();
				String[] temp2 = in2.split("\\s");
				String id2 = temp2[0];
				String cname2 = temp2[1];
				String grade2 = temp2[2];
				update(id2, cname2, grade2);
				break;
			case 3:
				System.out.println("������Ҫɾ���ɼ���ѧ����ѧ�ż��γ�����");
				Scanner read3 = new Scanner(System.in);
				String in3 = read3.nextLine();
				String[] temp3 = in3.split("\\s");
				String id3 = temp3[0];
				String cname3 = temp3[1];
				delete(id3, cname3);
				break;
			case 4:
				System.out.println("������γ�����");
				String cname4 = input.next();
				gradeBycou(cname4);
				break;
			case 5:
				notExit = false;
				break;
			default:
				System.out.println("��������");
				break;
			}
		}
	}

	// �޸�ѧ���ɼ�
	public void update(String id, String cname, String gra) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);

		String sql = "update grade set cgrade=? where cname=? and id=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, gra);
		ptmt.setString(2, cname);
		ptmt.setString(3, id);
		int rs = ptmt.executeUpdate();
		if (rs != 0) {
			System.out.println("���³ɹ���");
		}

		mylog.addLog(Loggable.TYPE.INFORMATION,  id + "ѧ��ѧ����" + cname + "�ɼ����޸���\n");
	}

	// ɾ��ѧ���ɼ�
	public void delete(String id, String cname) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);

		String sql = "delete from grade where id=? and cname=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, id);
		ptmt.setString(2, cname);
		int rs = ptmt.executeUpdate();
		if (rs != 0) {
			System.out.println("���³ɹ���");
		}
		mylog.addLog(Loggable.TYPE.INFORMATION,  id + "ѧ��ѧ����" + cname+"�ɼ���ɾ��\n");
	}

	// ���ѧ���ɼ�
	public void add(String id, String cname, String grade) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "insert into grade values (?,?,?)";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, id);
		ptmt.setString(2, cname);
		ptmt.setString(3, grade);
		int rs = ptmt.executeUpdate();
		if (rs != 0) {
			System.out.println("��ӳɹ���");
		}
		
		mylog.addLog(Loggable.TYPE.INFORMATION, "��ʦ�����ѧ��Ϊ"+id + "ѧ����" + cname+"�ɼ�\n" );
	}

	// ��ѯĳһѧ���ɼ�
	public void gradeById(String ID, String cname) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "select * from grade where id=? and cname=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, ID);
		ptmt.setString(2, cname);
		ResultSet rs1 = ptmt.executeQuery();
		System.out.println("\tѧ��\t�γ�\t�ɼ�\n");
		while (rs1.next()) {
			String id = rs1.getString(1);
			String name = rs1.getString(2);
			String gender = rs1.getString(3);
			System.out.println("\t" + id + "\t" + name + "\t" + gender + "\n");
		}
		System.out.println("���ҳɹ���");
		
		
	}

	// ��ѯ�γ�ȫ���ɼ�
	public void gradeBycou(String cname) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "select * from grade where cname=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, cname);
		ResultSet rs1 = ptmt.executeQuery();
		if(!rs1.next())
			System.out.println("����ʧ�ܣ������ڴ˿γ̣�");
		else {
			System.out.println("\tѧ��\t\t�ɼ�\n");
			while (rs1.next()) {
				String id = rs1.getString(1);
				String gender = rs1.getString(3);
				System.out.println("\t" + id + "\t" + gender);
			}
			System.out.println("���ҳɹ���");
		}
		
		mylog.addLog(Loggable.TYPE.INFORMATION, cname+"�ε���ʦ��ѯ����γ̳ɼ�");
	}

	// ���ñ��˿γ̣��޸Ŀγ�����
	public void updateCourse(String id, String cname, String cname2) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "update course set cname=? where cname=? and tid=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, cname2);
		ptmt.setString(2, cname);
		ptmt.setString(3, id);
		int rs = ptmt.executeUpdate();
		if (rs != 0) {
			System.out.println("���³ɹ���");
		} else {
			System.out.println("�Ǵ˿γ̽�ʦ������ʧ�ܣ�");
		}
		

		mylog.addLog(Loggable.TYPE.INFORMATION, id+"���ŵ���ʦ��ԭ��Ϊ"+cname+"�Ŀγ��޸�Ϊ"+cname2);
	}

}
