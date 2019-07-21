package model;

public class Student {
	private String id;// 学号
	private String name;
	private String sex;
	private String dep;// 院系
	private String tel;// 联系方式
	private String password; // 密码

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", dep=" + dep + ", sex=" + sex + ", tel=" + tel + "]";
	}

}
