package library.bean;

import java.io.Serializable;

public class UserBean implements Serializable {
	private String userId; 			//ユーザーID
	private String userPw;				//ユーザーPW
	private int borrowNum;				//借りている数
	private String dept;				//学部名
	private String name;				//氏名
	private int grade;					//学年
	private boolean ivailability;		//使用可否


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public int getBorrowNum() {
		return borrowNum;
	}
	public void setBorrowNum(int borrowNum) {
		this.borrowNum = borrowNum;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public boolean isIvailability() {
		return ivailability;
	}
	public void setIvailability(boolean ivailability) {
		this.ivailability = ivailability;
	}
}
