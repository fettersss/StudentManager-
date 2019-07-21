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
 * 模块说明： 普通老师具体功能的实现
 * 
 */

public class TeacherDAO {
	private static TeacherDAO ad = null;

	public static String url = AppConstants.JDBC_URL;
	public static String user = AppConstants.JDBC_USERNAME; // mysql登录名
	public static String pass = AppConstants.JDBC_PASSWORD;// mysql登录密码（写自己之前设置的）
	public static Connection con;

	MyLog mylog = new MyLog();
	static Scanner input = new Scanner(System.in);

	public static synchronized TeacherDAO getInstance() {
		if (ad == null) {
			ad = new TeacherDAO();
		}
		return ad;
	}

	// 普通老师查看本人所有课程信息
	public void queryForLessson(String id) throws SQLException {

		con = DriverManager.getConnection(url, user, pass);

		String sql = "select * from course where tid=?";
		PreparedStatement ptmt3 = con.prepareStatement(sql);
		ptmt3.setString(1, id);
		ResultSet rs1 = ptmt3.executeQuery();
		while (rs1.next()) {
			String name = rs1.getString(2);
			String gender = rs1.getString(3);
			System.out.println("\t课程名：" + name + "\t学分：" + gender+"\n");
		}
		System.out.println("查找成功！");

		mylog.addLog(Loggable.TYPE.INFORMATION, id + "工号的老师查看了自己的所有课程信息\n");
	}

	public void studentGrade() throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		boolean notExit = true;
		while (notExit) {
			System.out.println("**********学生成绩处理**********");
			System.out.println("\t请选择：\n \t1:添加成绩\n \t2:修改成绩\n \t3:删除成绩\n \t4:查看成绩\n \t5:退出\n");
			System.out.println("********************************");
			int i = input.nextInt();
			switch (i) {
			case 1:
				System.out.println("请输入要添加成绩的学生的学号、课程名、成绩：");
				Scanner read = new Scanner(System.in);
				String in = read.nextLine();
				String[] temp = in.split("\\s");
				String id = temp[0];
				String cname = temp[1];
				String grade = temp[2];
				add(id, cname, grade);
				break;
			case 2:
				System.out.println("请输入要修改成绩的学生的学号、课程名、成绩：");
				Scanner read2 = new Scanner(System.in);
				String in2 = read2.nextLine();
				String[] temp2 = in2.split("\\s");
				String id2 = temp2[0];
				String cname2 = temp2[1];
				String grade2 = temp2[2];
				update(id2, cname2, grade2);
				break;
			case 3:
				System.out.println("请输入要删除成绩的学生的学号及课程名：");
				Scanner read3 = new Scanner(System.in);
				String in3 = read3.nextLine();
				String[] temp3 = in3.split("\\s");
				String id3 = temp3[0];
				String cname3 = temp3[1];
				delete(id3, cname3);
				break;
			case 4:
				System.out.println("请输入课程名：");
				String cname4 = input.next();
				gradeBycou(cname4);
				break;
			case 5:
				notExit = false;
				break;
			default:
				System.out.println("输入有误！");
				break;
			}
		}
	}

	// 修改学生成绩
	public void update(String id, String cname, String gra) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);

		String sql = "update grade set cgrade=? where cname=? and id=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, gra);
		ptmt.setString(2, cname);
		ptmt.setString(3, id);
		int rs = ptmt.executeUpdate();
		if (rs != 0) {
			System.out.println("更新成功！");
		}

		mylog.addLog(Loggable.TYPE.INFORMATION,  id + "学号学生的" + cname + "成绩被修改了\n");
	}

	// 删除学生成绩
	public void delete(String id, String cname) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);

		String sql = "delete from grade where id=? and cname=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, id);
		ptmt.setString(2, cname);
		int rs = ptmt.executeUpdate();
		if (rs != 0) {
			System.out.println("更新成功！");
		}
		mylog.addLog(Loggable.TYPE.INFORMATION,  id + "学号学生的" + cname+"成绩被删除\n");
	}

	// 添加学生成绩
	public void add(String id, String cname, String grade) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "insert into grade values (?,?,?)";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, id);
		ptmt.setString(2, cname);
		ptmt.setString(3, grade);
		int rs = ptmt.executeUpdate();
		if (rs != 0) {
			System.out.println("添加成功！");
		}
		
		mylog.addLog(Loggable.TYPE.INFORMATION, "老师添加了学号为"+id + "学生的" + cname+"成绩\n" );
	}

	// 查询某一学生成绩
	public void gradeById(String ID, String cname) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "select * from grade where id=? and cname=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, ID);
		ptmt.setString(2, cname);
		ResultSet rs1 = ptmt.executeQuery();
		System.out.println("\t学号\t课程\t成绩\n");
		while (rs1.next()) {
			String id = rs1.getString(1);
			String name = rs1.getString(2);
			String gender = rs1.getString(3);
			System.out.println("\t" + id + "\t" + name + "\t" + gender + "\n");
		}
		System.out.println("查找成功！");
		
		
	}

	// 查询课程全部成绩
	public void gradeBycou(String cname) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "select * from grade where cname=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, cname);
		ResultSet rs1 = ptmt.executeQuery();
		if(!rs1.next())
			System.out.println("查找失败，不存在此课程！");
		else {
			System.out.println("\t学号\t\t成绩\n");
			while (rs1.next()) {
				String id = rs1.getString(1);
				String gender = rs1.getString(3);
				System.out.println("\t" + id + "\t" + gender);
			}
			System.out.println("查找成功！");
		}
		
		mylog.addLog(Loggable.TYPE.INFORMATION, cname+"课的老师查询了其课程成绩");
	}

	// 设置本人课程（修改课程名）
	public void updateCourse(String id, String cname, String cname2) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "update course set cname=? where cname=? and tid=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, cname2);
		ptmt.setString(2, cname);
		ptmt.setString(3, id);
		int rs = ptmt.executeUpdate();
		if (rs != 0) {
			System.out.println("更新成功！");
		} else {
			System.out.println("非此课程教师，更新失败！");
		}
		

		mylog.addLog(Loggable.TYPE.INFORMATION, id+"工号的老师将原名为"+cname+"的课程修改为"+cname2);
	}

}
