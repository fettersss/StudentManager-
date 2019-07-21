package view;

import dao.DeanDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import java.sql.*;
import java.util.*;

public class MainView {

	/**
	 * ����ѡ�����
	 */

	static Scanner input = new Scanner(System.in);
	static TeacherDAO tea = new TeacherDAO();
	static StudentDAO stu = new StudentDAO();
	static DeanDAO dea = new DeanDAO();
	static Scanner scanner = new Scanner(System.in);

	public static void printUI1(String username) throws SQLException {
		boolean notExit = true;
		while (notExit) {
			System.out.println("**********ѧ�����ܽ���**********");
			System.out.println("\t��ѡ��\n \t1:��ѯ������Ϣ\n \t2:�鿴�ɼ�\n \t3:�˳�\n");
			System.out.println("********************************");
			int i = input.nextInt();
			switch (i) {
			case 1:
				stu.queryById(username);
				break;
			case 2:
				stu.gradeById(username);
				break;
			case 3:
				notExit = false;
				System.out.println("�˳��ɹ�����ӭ�´�ʹ�ã�");
				System.exit(0);
			default:
				System.out.println("��������");
				break;
				
			}
		}
	}

	public static void printUI2(String username) throws SQLException {
		boolean notExit = true;
		while (notExit) {
			System.out.println("**********��ͨ��ʦ���ܽ���**********");
			System.out.println("\t��ѡ��\n \t1:���ÿγ�\n \t2:�鿴���˿γ���Ϣ\n \t3.����ѧ���ɼ�\n \t4:�˳�\n");
			System.out.println("************************************");
			int i = input.nextInt();
			switch (i) {
			case 1:
				System.out.println("�������ʼ�γ��������޸Ŀγ��� ��");
				
				Scanner read = new Scanner(System.in);
				String in = read.nextLine();
				String[] temp = in.split("\\s");

				String cname1 = temp[0];
				String cname2 = temp[1];
				tea.updateCourse(username, cname1, cname2);
				break;
			case 2:
				// System.out.println("��������Ľ̹��ţ�");
				// String id=input.next();
				/*
				 * String sql3 = "select * from course where tid=?"; PreparedStatement ptmt3 =
				 * con.prepareStatement(sql3); ptmt3.setString(1, id); ResultSet rs1 =
				 * ptmt3.executeQuery(); while(rs1.next()){
				 * 
				 * String name = rs1.getString(2); String gender = rs1.getString(3);
				 * System.out.println("  �γ�����"+name+"      ѧ�֣�"+gender); }
				 * System.out.println("���ҳɹ���");
				 */

				// TeacherDAO tea2 = new TeacherDAO();
				tea.queryForLessson(username);
				break;
			case 3:
				tea.studentGrade();
				break;
			case 4:
				notExit = false;
				System.out.println("�˳��ɹ�����ӭ�´�ʹ�ã�");
				System.exit(0);
			default:
				System.out.println("��������");
				break;
			}
		}
	}

	public static void printUI3(String username) throws SQLException {
		boolean notExit = true;
		Scanner scanner = new Scanner(System.in);
		while (notExit) {
			System.out.println("**********�̵�����ʦ�˵�**********\n");
			System.out.println("\t��ѡ��\n \t1.����ѧ����Ϣ\n \t2.���ÿγ���Ϣ\n \t3.��ѯ�γ̳ɼ�\n \t4.�˳�\n");
			System.out.println("**********************************");
			int i = input.nextInt();
			switch (i) {
			case 1:
				dea.updateStu();
				break;
			case 2:
				dea.updateCou();
				break;
			case 3:
				System.out.println("������Ҫ��ѯ�ɼ��Ŀγ�����");
				String input = scanner.nextLine();
				dea.gradeById(input);
				break;
			case 4:
				notExit = false;
				System.out.println("�˳��ɹ�����ӭ�´�ʹ�ã�");
				System.exit(0);
			default:
				System.out.println("�����������������룺");
				break;
			}

		}
	}

}