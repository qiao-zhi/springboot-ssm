/*
 * This file is generated by jOOQ.
 */
package cn.qlq.jooq.tables.pojos;


import cn.qlq.jooq.tables.interfaces.IUser;

import javax.annotation.Generated;
import java.sql.Date;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class User implements IUser {

    private static final long serialVersionUID = 353893098;

    private Integer id;
    private String  username;
    private String  password;
    private String  userfullname;
    private Date    createtime;
    private String  isdeleted;
    private String  sex;
    private String  address;
    private String  roles;
    private String  userblank;

    public User() {}

    public User(IUser value) {
        this.id = value.getId();
        this.username = value.getUsername();
        this.password = value.getPassword();
        this.userfullname = value.getUserfullname();
        this.createtime = value.getCreatetime();
        this.isdeleted = value.getIsdeleted();
        this.sex = value.getSex();
        this.address = value.getAddress();
        this.roles = value.getRoles();
        this.userblank = value.getUserblank();
    }

    public User(
        Integer id,
        String  username,
        String  password,
        String  userfullname,
        Date    createtime,
        String  isdeleted,
        String  sex,
        String  address,
        String  roles,
        String  userblank
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userfullname = userfullname;
        this.createtime = createtime;
        this.isdeleted = isdeleted;
        this.sex = sex;
        this.address = address;
        this.roles = roles;
        this.userblank = userblank;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUserfullname() {
        return this.userfullname;
    }

    @Override
    public void setUserfullname(String userfullname) {
        this.userfullname = userfullname;
    }

    @Override
    public Date getCreatetime() {
        return this.createtime;
    }

    @Override
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String getIsdeleted() {
        return this.isdeleted;
    }

    @Override
    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }

    @Override
    public String getSex() {
        return this.sex;
    }

    @Override
    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getRoles() {
        return this.roles;
    }

    @Override
    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String getUserblank() {
        return this.userblank;
    }

    @Override
    public void setUserblank(String userblank) {
        this.userblank = userblank;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("User (");

        sb.append(id);
        sb.append(", ").append(username);
        sb.append(", ").append(password);
        sb.append(", ").append(userfullname);
        sb.append(", ").append(createtime);
        sb.append(", ").append(isdeleted);
        sb.append(", ").append(sex);
        sb.append(", ").append(address);
        sb.append(", ").append(roles);
        sb.append(", ").append(userblank);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IUser from) {
        setId(from.getId());
        setUsername(from.getUsername());
        setPassword(from.getPassword());
        setUserfullname(from.getUserfullname());
        setCreatetime(from.getCreatetime());
        setIsdeleted(from.getIsdeleted());
        setSex(from.getSex());
        setAddress(from.getAddress());
        setRoles(from.getRoles());
        setUserblank(from.getUserblank());
    }

    @Override
    public <E extends IUser> E into(E into) {
        into.from(this);
        return into;
    }
}
