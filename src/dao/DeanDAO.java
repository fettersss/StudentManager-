package dao;

import java.sql.*;
import java.util.*;

import Constants.AppConstants;
import model.Student;
import Log.Loggable;

/**
 * 模块说明： 教导处老师的具体功能实现
 * 
 */

public class DeanDAO {
	private static DeanDAO ad = null;
	private static Log.MyLog mylog = new Log.MyLog();
	public static String url = AppConstants.JDBC_URL;
	public static String user = AppConstants.JDBC_USERNAME; // mysql登录名
	public static String pass = AppConstants.JDBC_PASSWORD;// mysql登录密码（写自己之前设置的）
	public static Connection con;//
	Scanner input = new Scanner(System.in);

	public static synchronized DeanDAO getInstance() {
		if (ad == null) {
			ad = new DeanDAO();
		}
		return ad;
	}

	// 修改学生信息
	public void updateStu() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		boolean notExit = true;
		String id, name, sex, dep, tel, password;
		while (notExit) {
			System.out.println("**********学生信息处理**********");
			System.out.println("\t请选择：\n \t1.查询学生信息\n \t2.修改学生院系\n \t3.修改学生联系方式\n \t4.添加新学生信息\n \t5.退出\n");
			System.out.println("********************************");
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				studentInfo();
				break;
			case 2:
				System.out.println("请输入学生学号和新的院系：");
				String input1 = scanner.nextLine();
				String[] temp1 = input1.split("\\s");
				id = temp1[0];
				dep = temp1[1];
				updateStuDep(id, dep);
				break;
			case 3:
				System.out.println("请输入学生学号和新的联系方式：");
				String input2 = scanner.nextLine();
				String[] temp2 = input2.split("\\s");
				id = temp2[0];
				tel = temp2[1];
				updateStuTel(id, tel);
				break;
			case 4:
				System.out.println("请输入添加的新学生信息（学号 姓名 性别 院系 联系方式 密码）:");
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
				System.out.println("输入有误！请重新输入：");
				break;
			}
		}
	}

	// 查询所有学生信息
	public void studentInfo() throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "select * from student";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ResultSet rs = ptmt.executeQuery();
		System.out.println("\t学号\t\t姓名\t性别\t院系\t联系方式\n");
		while (rs.next()) {
			String id = rs.getString(1);
			String name = rs.getString(2);
			String sex = rs.getString(3);
			String dep = rs.getString(4);
			String tel = rs.getString(5);
			System.out.println("\t" + id + "\t" + name + "\t" + sex + "\t" + dep + "\t" + tel);
		}
	}

	// 修改学生院系
	public void updateStuDep(String id, String dep) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "update student set dep=? where id=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, dep);
		ptmt.setString(2, id);
		int rs = ptmt.executeUpdate();
		if (rs != 0) {
			System.out.println("更新成功！");
			mylog.addLog(Loggable.TYPE.INFORMATION, "教导老师将" + id + "学号学生的院系信息修改为" + dep + "\n");
		} else
			System.out.println("更新失败，此学生不存在！");

	}

	// 修改学生联系方式
	public void updateStuTel(String id, String tel) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		if (id == null) {
			System.out.println("此学生不存在");
		} else {
			String sql = "update student set tele=? where id=?";
			PreparedStatement ptmt = con.prepareStatement(sql);
			ptmt.setString(1, tel);
			ptmt.setString(2, id);
			int rs = ptmt.executeUpdate();
			if (rs != 0) {
				System.out.println("更新成功！");
			}
			mylog.addLog(Loggable.TYPE.INFORMATION, "教导老师将" + id + "学号学生的联系方式修改为" + tel + "\n");
		}

	}

	// 查询特定学号的学生
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

	// 添加新学生的信息
	public void addStu(String id, String name, String sex, String dep, String tel, String password)
			throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		if (queryById(id) == true) {
			System.out.println("此学号的学生已存在！");
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
				System.out.println("更新成功！");
			}
			mylog.addLog(Loggable.TYPE.INFORMATION, "教导老师添加了新学生的信息：学号为" + id + "姓名为：" + name + "性别为：" + sex + "院系为："
					+ dep + "联系方式为：" + tel + "初始密码为：" + password + "\n");
		}

	}

	// 设置课程信息
	public void updateCou() throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		Scanner scanner = new Scanner(System.in);
		boolean notExit = true;
		String tid, cname, credit, newtid, newcname;
		while (notExit) {
			System.out.println("**********课程信息处理**********");
			System.out.println("\t请选择：\n \t1.查询课程信息\n \t2.修改课程教师\n \t3.修改课程名\n \t4.修改课程学分\n \t5.添加新课程\n \t6.退出\n");
			System.out.println("*****************************");
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				queryCou();
				break;
			case 2:
				System.out.println("请输入原教工号，课程和新教工号：");
				String input1 = scanner.nextLine();
				String[] temp1 = input1.split("\\s");
				tid = temp1[0];
				cname = temp1[1];
				newtid = temp1[2];
				updateCouT(tid, cname, newtid);
				break;
			case 3:
				System.out.println("请输入教工号，原课程名和新课程名：");
				String input2 = scanner.nextLine();
				String[] temp2 = input2.split("\\s");
				tid = temp2[0];
				cname = temp2[1];
				newcname = temp2[2];
				updateCouN(tid, cname, newcname);
				break;
			case 4:
				System.out.println("请输入教工号，课程名和新学分：");
				String input3 = scanner.nextLine();
				String[] temp3 = input3.split("\\s");
				tid = temp3[0];
				cname = temp3[1];
				credit = temp3[2];
				updateCouC(tid, cname, credit);
				break;
			case 5:
				System.out.println("请输入新课程的教工号，课程名和学分：");
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
				System.out.println("输入有误！请重新输入：");
				break;
			}
		}

	}

	// 查询特定课程
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

	// 查询课程信息
	public void queryCou() throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "select * from course";
		PreparedStatement ptmt = con.prepareStatement(sql);

		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			String id = rs.getString(1);
			String name = rs.getString(2);
			String sex = rs.getString(3);
			System.out.println("\t教工号：" + id + "\t课程：" + name + "\t学分：" + sex);
		}
	}

	// 修改课程教师
	public void updateCouT(String tid, String cname, String newtid) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "update course set tid=? where tid=? and cname=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, newtid);
		ptmt.setString(2, tid);
		ptmt.setString(3, cname);
		int rs = ptmt.executeUpdate();
		if (rs == 0) {
			System.out.println("此课程不存在！");
		} else {
			System.out.println("更新成功！");
			mylog.addLog(Loggable.TYPE.INFORMATION, "教导老师将" + cname + "课程的教师修改为" + newtid + "\n");
		}

	}

	// 修改课程名
	public void updateCouN(String tid, String cname, String newcname) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "update course set cname=? where tid=? and cname=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, newcname);
		ptmt.setString(2, tid);
		ptmt.setString(3, cname);
		int rs = ptmt.executeUpdate();
		if (rs == 0) {
			System.out.println("此课程不存在！");
		} else {
			System.out.println("更新成功！");
			mylog.addLog(Loggable.TYPE.INFORMATION, "教导老师将" + tid + "教师教授的课程修改为" + newcname + "\n");
		}

	}

	// 修改课程学分
	public void updateCouC(String tid, String cname, String credit) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		String sql = "update course set credit=? where tid=? and cname=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, credit);
		ptmt.setString(2, tid);
		ptmt.setString(3, cname);
		int rs = ptmt.executeUpdate();
		if (rs == 0) {
			System.out.println("此课程不存在！");
		} else {
			System.out.println("更新成功！");
			mylog.addLog(Loggable.TYPE.INFORMATION, "教导老师将" + tid + "教师的" + cname + "课程的学分修改为" + credit + "\n");
		}

	}

	// 添加新课程的信息
	public void addCou(String tid, String cname, String credit) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		if (queryCouse(tid, cname) == true) {
			System.out.println("此课程已存在！");
		} else {
			String sql = "insert into course values (?,?,?)";
			PreparedStatement ptmt = con.prepareStatement(sql);
			ptmt.setString(1, tid);
			ptmt.setString(2, cname);
			ptmt.setString(3, credit);
			int rs = ptmt.executeUpdate();
			if (rs != 0) {
				System.out.println("更新成功！");
			}
			mylog.addLog(Loggable.TYPE.INFORMATION,
					"教导老师添加了新课程的信息：教工号为" + tid + "课程名为：" + cname + "学分为：" + credit + "\n");
		}

	}

	// 查询课程成绩
	public void gradeById(String Cname) throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		if (Cname == null) {
			System.out.println("此课程不存在！");
		} else {
			String sql = "select * from grade where cname=?";
			PreparedStatement ptmt = con.prepareStatement(sql);
			ptmt.setString(1, Cname);
			ResultSet rs = ptmt.executeQuery();
			System.out.println("\t学号\t\t学分\n");
			while (rs.next()) {
				String id = rs.getString(1);
				String cgrade = rs.getString(3);
				System.out.println("\t" + id + "\t" + cgrade + "\n");
			}
			System.out.println("查找成功！");
		}

	}

}