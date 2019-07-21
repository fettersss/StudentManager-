package Log;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyLog implements Loggable {
	File mylog = new File("MyLog.log");
	String logContent;
	TYPE type;

	public void addLog(TYPE type, String logContent) {

		try {

			FileWriter write = new FileWriter(mylog, true); // 参数true使得写入内容可以接在文件的已有内容后面
			BufferedWriter bwrite = new BufferedWriter(write);
			Date nowTime = new Date();
			SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time = matter.format(nowTime);
			bwrite.write("*****************************");
			bwrite.newLine();
			bwrite.write(time);
			bwrite.newLine();
			bwrite.write(type.toString());
			bwrite.newLine();
			bwrite.write(logContent);
			bwrite.newLine();
			bwrite.write("*****************************/n");
			bwrite.newLine();
			bwrite.close();
			write.close();

		} catch (IOException e) {
			System.out.println(e);
		}
	}
	/*
	 * public String readLog() { try { FileReader inOne = new FileReader(mylog);
	 * BufferedReader inTwo = new BufferedReader(inOne); String s = null; while ((s
	 * = inTwo.readLine()) != null) { System.out.println(s); } inOne.close();
	 * inTwo.close();
	 * 
	 * } catch (IOException e) { System.out.println(e); } return null; }
	 */}
