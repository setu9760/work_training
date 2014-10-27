package main.java.com.action;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	private String username;
	private String pwd1;
	private String pwd2;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd1() {
		return pwd1;
	}

	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}

	public String getPwd2() {
		return pwd2;
	}

	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}

	@Override
	public String execute() throws Exception {
		if ("setu".equalsIgnoreCase(username) && (pwd1.equals(pwd2))) {
			return SUCCESS;
		}
		if (!pwd1.equals(pwd2)) {
			addActionMessage("Passwords do not match");
		}
		return INPUT;
	}
}
