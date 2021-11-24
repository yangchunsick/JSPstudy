package dto;

public class Student {
	
	private String sno;
	private String name;
	private int	midterm;
	private int finalterm;
	private String pass;
	
	/**/
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMidterm() {
		return midterm;
	}
	public void setMidterm(int midterm) {
		this.midterm = midterm;
	}
	public int getFinalterm() {
		return finalterm;
	}
	public void setFinalterm(int finalterm) {
		this.finalterm = finalterm;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}
