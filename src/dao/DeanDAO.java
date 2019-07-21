package dao;

import java.sql.*;
import java.util.*;

import Constants.AppConstants;
import model.Student;
import Log.Loggable;

/**
 * ģ��˵���� �̵�����ʦ�ľ��幦��ʵ��
 * 
 */

public class DeanDAO {
	private static DeanDAO ad = null;
	private static Log.MyLog mylog = new Log.MyLog();
	public static String url = AppConstants.JDBC_URL;
	public static String user = AppConstants.JDBC_USERNAME; // mysql��¼��
	public static String pass = AppConstants.JDBC_PASSWORD;// mysql��¼���루д�Լ�֮ǰ���õģ�
	public static Connection con;//
	Scanner input = new Scanner(System.in);

	public static synchronized DeanDAO getInstance() {
		if (ad == null) {
			ad = new DeanDAO();
		}
		return ad;
	}

	// �޸�ѧ����Ϣ
	public void updateStu() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		boolean notExit = true;
		String id, name, sex, dep, tel, password;
		while (notExit) {
			System.out.println("**********ѧ����Ϣ����**********");
			System.out.println("\t��ѡ��\n \t1.��ѯѧ����Ϣ\n \t2.�޸�ѧ��Ժϵ\n \t3.�޸�ѧ����ϵ��ʽ\n \t4.�����ѧ����Ϣ\n \t5.�˳�\n");
			System.out.println("********************************");
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				studentInfo();
				break;
			case 2:
				System.out.println("������ѧ��ѧ�ź��µ�Ժϵ��");
				String input1 = scanner.nextLine();
				String[] temp1 = input1.split("\\s");
				id = temp1[0];
				dep = temp1[1];
				updateStuDep(id, dep);
				break;
			case 3:
				System.out.println("������ѧ��ѧ�ź��µ���ϵ��ʽ��");
				String input2 = scanner.nextLine();
				String[] temp2 = input2.split("\\s");
				id = temp2[0];
				tel = temp2[1];
				updateStuTel(id, tel);
				break;
			case 4:
				System.out.println("��������ӵ���ѧ����Ϣ��ѧ�� ���� �Ա� Ժϵ ��ϵ��ʽ ���룩:");
				String input3 = scanner.nextLine();
				String[] temp3 = input3.split("\\s");
				id = temp3[0];
				name = temp3[1];
				sex = temp3[2];
				dep = temp3[3];
				tel = temp3[4];
				password = temp3[5];
				addStu(id, name, sex, dep, tel, password);
				break;
			case 5:
				notExit = false;
				break;
			default:
				System.out.println("�����������������룺");
				break;
			}
		}
	}

	// ��ѯ����ѧ����Ϣ
	public void studentInfo() throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "select * from student";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ResultSet rs = ptmt.executeQuery();
		System.out.println("\tѧ��\t\t����\t�Ա�\tԺϵ\t��ϵ��ʽ\n");
		while (rs.next()) {
			String id = rs.getString(1);
			String name = rs.getString(2);
			String sex = rs.getString(3);
			String dep = rs.getString(4);
			String tel = rs.getString(5);
			System.out.println("\t" + id + "\t" + name + "\t" + sex + "\t" + dep + "\t" + tel);
		}
	}

	// �޸�ѧ��Ժϵ
	public void updateStuDep(String id, String dep) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "update student set dep=? where id=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, dep);
		ptmt.setString(2, id);
		int rs = ptmt.executeUpdate();
		if (rs != 0) {
			System.out.println("���³ɹ���");
			mylog.addLog(Loggable.TYPE.INFORMATION, "�̵���ʦ��" + id + "ѧ��ѧ����Ժϵ��Ϣ�޸�Ϊ" + dep + "\n");
		} else
			System.out.println("����ʧ�ܣ���ѧ�������ڣ�");

	}

	// �޸�ѧ����ϵ��ʽ
	public void updateStuTel(String id, String tel) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		if (id == null) {
			System.out.println("��ѧ��������");
		} else {
			String sql = "update student set tele=? where id=?";
			PreparedStatement ptmt = con.prepareStatement(sql);
			ptmt.setString(1, tel);
			ptmt.setString(2, id);
			int rs = ptmt.executeUpdate();
			if (rs != 0) {
				System.out.println("���³ɹ���");
			}
			mylog.addLog(Loggable.TYPE.INFORMATION, "�̵���ʦ��" + id + "ѧ��ѧ������ϵ��ʽ�޸�Ϊ" + tel + "\n");
		}

	}

	// ��ѯ�ض�ѧ�ŵ�ѧ��
	public boolean queryById(String ID) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "select * from student where id=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, ID);
		ResultSet rs = ptmt.executeQuery();
		if (rs.next()) {
			return true;
		} else
			return false;
	}

	// �����ѧ������Ϣ
	public void addStu(String id, String name, String sex, String dep, String tel, String password)
			throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		if (queryById(id) == true) {
			System.out.println("��ѧ�ŵ�ѧ���Ѵ��ڣ�");
		} else {
			String sql = "insert into student values (?,?,?,?,?,?)";
			PreparedStatement ptmt = con.prepareStatement(sql);
			ptmt.setString(1, id);
			ptmt.setString(2, name);
			ptmt.setString(3, sex);
			ptmt.setString(4, dep);
			ptmt.setString(5, tel);
			ptmt.setString(6, password);
			int rs = ptmt.executeUpdate();
			if (rs != 0) {
				System.out.println("���³ɹ���");
			}
			mylog.addLog(Loggable.TYPE.INFORMATION, "�̵���ʦ�������ѧ������Ϣ��ѧ��Ϊ" + id + "����Ϊ��" + name + "�Ա�Ϊ��" + sex + "ԺϵΪ��"
					+ dep + "��ϵ��ʽΪ��" + tel + "��ʼ����Ϊ��" + password + "\n");
		}

	}

	// ���ÿγ���Ϣ
	public void updateCou() throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		Scanner scanner = new Scanner(System.in);
		boolean notExit = true;
		String tid, cname, credit, newtid, newcname;
		while (notExit) {
			System.out.println("**********�γ���Ϣ����**********");
			System.out.println("\t��ѡ��\n \t1.��ѯ�γ���Ϣ\n \t2.�޸Ŀγ̽�ʦ\n \t3.�޸Ŀγ���\n \t4.�޸Ŀγ�ѧ��\n \t5.����¿γ�\n \t6.�˳�\n");
			System.out.println("*****************************");
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				queryCou();
				break;
			case 2:
				System.out.println("������ԭ�̹��ţ��γ̺��½̹��ţ�");
				String input1 = scanner.nextLine();
				String[] temp1 = input1.split("\\s");
				tid = temp1[0];
				cname = temp1[1];
				newtid = temp1[2];
				updateCouT(tid, cname, newtid);
				break;
			case 3:
				System.out.println("������̹��ţ�ԭ�γ������¿γ�����");
				String input2 = scanner.nextLine();
				String[] temp2 = input2.split("\\s");
				tid = temp2[0];
				cname = temp2[1];
				newcname = temp2[2];
				updateCouN(tid, cname, newcname);
				break;
			case 4:
				System.out.println("������̹��ţ��γ�������ѧ�֣�");
				String input3 = scanner.nextLine();
				String[] temp3 = input3.split("\\s");
				tid = temp3[0];
				cname = temp3[1];
				credit = temp3[2];
				updateCouC(tid, cname, credit);
				break;
			case 5:
				System.out.println("�������¿γ̵Ľ̹��ţ��γ�����ѧ�֣�");
				String input4 = scanner.nextLine();
				String[] temp4 = input4.split("\\s");
				tid = temp4[0];
				cname = temp4[1];
				credit = temp4[2];
				addCou(tid, cname, credit);
				break;
			case 6:
				notExit = false;
				break;
			default:
				System.out.println("�����������������룺");
				break;
			}
		}

	}

	// ��ѯ�ض��γ�
	public boolean queryCouse(String tid, String cname) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "select * from course where tid=? and cname=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, tid);
		ptmt.setString(2, cname);
		ResultSet rs = ptmt.executeQuery();
		if (rs.next()) {
			return true;
		} else
			return false;
	}

	// ��ѯ�γ���Ϣ
	public void queryCou() throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "select * from course";
		PreparedStatement ptmt = con.prepareStatement(sql);

		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			String id = rs.getString(1);
			String name = rs.getString(2);
			String sex = rs.getString(3);
			System.out.println("\t�̹��ţ�" + id + "\t�γ̣�" + name + "\tѧ�֣�" + sex);
		}
	}

	// �޸Ŀγ̽�ʦ
	public void updateCouT(String tid, String cname, String newtid) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "update course set tid=? where tid=? and cname=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, newtid);
		ptmt.setString(2, tid);
		ptmt.setString(3, cname);
		int rs = ptmt.executeUpdate();
		if (rs == 0) {
			System.out.println("�˿γ̲����ڣ�");
		} else {
			System.out.println("���³ɹ���");
			mylog.addLog(Loggable.TYPE.INFORMATION, "�̵���ʦ��" + cname + "�γ̵Ľ�ʦ�޸�Ϊ" + newtid + "\n");
		}

	}

	// �޸Ŀγ���
	public void updateCouN(String tid, String cname, String newcname) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "update course set cname=? where tid=? and cname=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, newcname);
		ptmt.setString(2, tid);
		ptmt.setString(3, cname);
		int rs = ptmt.executeUpdate();
		if (rs == 0) {
			System.out.println("�˿γ̲����ڣ�");
		} else {
			System.out.println("���³ɹ���");
			mylog.addLog(Loggable.TYPE.INFORMATION, "�̵���ʦ��" + tid + "��ʦ���ڵĿγ��޸�Ϊ" + newcname + "\n");
		}

	}

	// �޸Ŀγ�ѧ��
	public void updateCouC(String tid, String cname, String credit) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "update course set credit=? where tid=? and cname=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, credit);
		ptmt.setString(2, tid);
		ptmt.setString(3, cname);
		int rs = ptmt.executeUpdate();
		if (rs == 0) {
			System.out.println("�˿γ̲����ڣ�");
		} else {
			System.out.println("���³ɹ���");
			mylog.addLog(Loggable.TYPE.INFORMATION, "�̵���ʦ��" + tid + "��ʦ��" + cname + "�γ̵�ѧ���޸�Ϊ" + credit + "\n");
		}

	}

	// ����¿γ̵���Ϣ
	public void addCou(String tid, String cname, String credit) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		if (queryCouse(tid, cname) == true) {
			System.out.println("�˿γ��Ѵ��ڣ�");
		} else {
			String sql = "insert into course values (?,?,?)";
			PreparedStatement ptmt = con.prepareStatement(sql);
			ptmt.setString(1, tid);
			ptmt.setString(2, cname);
			ptmt.setString(3, credit);
			int rs = ptmt.executeUpdate();
			if (rs != 0) {
				System.out.println("���³ɹ���");
			}
			mylog.addLog(Loggable.TYPE.INFORMATION,
					"�̵���ʦ������¿γ̵���Ϣ���̹���Ϊ" + tid + "�γ���Ϊ��" + cname + "ѧ��Ϊ��" + credit + "\n");
		}

	}

	// ��ѯ�γ̳ɼ�
	public void gradeById(String Cname) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		if (Cname == null) {
			System.out.println("�˿γ̲����ڣ�");
		} else {
			String sql = "select * from grade where cname=?";
			PreparedStatement ptmt = con.prepareStatement(sql);
			ptmt.setString(1, Cname);
			ResultSet rs = ptmt.executeQuery();
			System.out.println("\tѧ��\t\tѧ��\n");
			while (rs.next()) {
				String id = rs.getString(1);
				String cgrade = rs.getString(3);
				System.out.println("\t" + id + "\t" + cgrade + "\n");
			}
			System.out.println("���ҳɹ���");
		}

	}

}