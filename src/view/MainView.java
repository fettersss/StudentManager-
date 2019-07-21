package view;

import dao.DeanDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import java.sql.*;
import java.util.*;

public class MainView {

	/**
	 * 功能选择界面
	 */

	static Scanner input = new Scanner(System.in);
	static TeacherDAO tea = new TeacherDAO();
	static StudentDAO stu = new StudentDAO();
	static DeanDAO dea = new DeanDAO();
	static Scanner scanner = new Scanner(System.in);

	public static void printUI1(String username) throws SQLException {
		boolean notExit = true;
		while (notExit) {
			System.out.println("**********学生功能界面**********");
			System.out.println("\t请选择：\n \t1:查询本人信息\n \t2:查看成绩\n \t3:退出\n");
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
				System.out.println("退出成功，欢迎下次使用！");
				System.exit(0);
			default:
				System.out.println("输入有误！");
				break;
				
			}
		}
	}

	public static void printUI2(String username) throws SQLException {
		boolean notExit = true;
		while (notExit) {
			System.out.println("**********普通老师功能界面**********");
			System.out.println("\t请选择：\n \t1:设置课程\n \t2:查看个人课程信息\n \t3.处理学生成绩\n \t4:退出\n");
			System.out.println("************************************");
			int i = input.nextInt();
			switch (i) {
			case 1:
				System.out.println("请输入初始课程名，及修改课程名 ：");
				
				Scanner read = new Scanner(System.in);
				String in = read.nextLine();
				String[] temp = in.split("\\s");

				String cname1 = temp[0];
				String cname2 = temp[1];
				tea.updateCourse(username, cname1, cname2);
				break;
			case 2:
				// System.out.println("请输入你的教工号：");
				// String id=input.next();
				/*
				 * String sql3 = "select * from course where tid=?"; PreparedStatement ptmt3 =
				 * con.prepareStatement(sql3); ptmt3.setString(1, id); ResultSet rs1 =
				 * ptmt3.executeQuery(); while(rs1.next()){
				 * 
				 * String name = rs1.getString(2); String gender = rs1.getString(3);
				 * System.out.println("  课程名："+name+"      学分："+gender); }
				 * System.out.println("查找成功！");
				 */

				// TeacherDAO tea2 = new TeacherDAO();
				tea.queryForLessson(username);
				break;
			case 3:
				tea.studentGrade();
				break;
			case 4:
				notExit = false;
				System.out.println("退出成功，欢迎下次使用！");
				System.exit(0);
			default:
				System.out.println("输入有误！");
				break;
			}
		}
	}

	public static void printUI3(String username) throws SQLException {
		boolean notExit = true;
		Scanner scanner = new Scanner(System.in);
		while (notExit) {
			System.out.println("**********教导处教师菜单**********\n");
			System.out.println("\t请选择：\n \t1.处理学生信息\n \t2.设置课程信息\n \t3.查询课程成绩\n \t4.退出\n");
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
				System.out.println("请输入要查询成绩的课程名：");
				String input = scanner.nextLine();
				dea.gradeById(input);
				break;
			case 4:
				notExit = false;
				System.out.println("退出成功，欢迎下次使用！");
				System.exit(0);
			default:
				System.out.println("输入有误！请重新输入：");
				break;
			}

		}
	}

}