package dto;

import java.sql.Date;

public class Member {

	private String no;
	private String name;
	private int	age;
	private String birthday;
	private Date regdate;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "Member [no=" + no + ", name=" + name + ", age=" + age + ", birthday=" + birthday + ", regdate="
				+ regdate + "]";
	}	
	
	
}
