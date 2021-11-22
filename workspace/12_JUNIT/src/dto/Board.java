package dto;

import java.sql.Date;

public class Board {
	
	private String bNo;
	private String writer;
	private String content;
	private Date bDate;
	
	/*	dovob	*/
	
	public String getbNo() {
		return bNo;
	}
	public void setbNo(String bNo) {
		this.bNo = bNo;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getbDate() {
		return bDate;
	}
	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}
	
	
	@Override
	public String toString() {
		return "Board [bNo=" + bNo + ", writer=" + writer + ", content=" + content + ", bDate=" + bDate + "]";
	}
	
	// ToString을 하는 이유는 이 놈들이 넘어왔나 안 넘어왔나 점검하는 코드 같은 느낌임
	// 이곳에서 ToString을 하고 넘어간 곳에 System.out(list.toString()); 찍어보면 앎
	
	
}
