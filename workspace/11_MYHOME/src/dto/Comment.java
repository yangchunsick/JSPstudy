package dto;

import java.sql.Date;

public class Comment {

	private Long cNo;
	private String writer;
	private String content;
	private Long bNo;
	private Long state;
	private Date created;
	
	/**/
	public Long getcNo() {
		return cNo;
	}
	public void setcNo(Long cNo) {
		this.cNo = cNo;
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
	public Long getbNo() {
		return bNo;
	}
	public void setbNo(Long bNo) {
		this.bNo = bNo;
	}
	public Long getState() {
		return state;
	}
	public void setState(Long state) {
		this.state = state;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
}
