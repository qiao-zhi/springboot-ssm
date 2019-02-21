package cn.qlq.bean.user;

import java.util.Date;

public class User {
	private Integer id;

	private String username;

	private String password;

	private String userfullname;

	private Date createtime;

	private String isdeleted;

	private String sex;

	private String address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getUserfullname() {
		return userfullname;
	}

	public void setUserfullname(String userfullname) {
		this.userfullname = userfullname == null ? null : userfullname.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted == null ? null : isdeleted.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", userfullname=" + userfullname
				+ ", createtime=" + createtime + ", isdeleted=" + isdeleted + ", sex=" + sex + ", address=" + address
				+ "]";
	}
}