package cn.qlq.bean.user;

import java.util.Date;

public class Token {
	private Integer id;

	private Date createtime;

	private String username;

	private String tokenstr;

	private Date losetime;

	private String userfullname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getTokenstr() {
		return tokenstr;
	}

	public void setTokenstr(String tokenstr) {
		this.tokenstr = tokenstr == null ? null : tokenstr.trim();
	}

	public Date getLosetime() {
		return losetime;
	}

	public void setLosetime(Date losetime) {
		this.losetime = losetime;
	}

	public String getUserfullname() {
		return userfullname;
	}

	public void setUserfullname(String userfullname) {
		this.userfullname = userfullname == null ? null : userfullname.trim();
	}

	@Override
	public String toString() {
		return "Token [id=" + id + ", createtime=" + createtime + ", username=" + username + ", tokenstr=" + tokenstr
				+ ", losetime=" + losetime + ", userfullname=" + userfullname + "]";
	}
}