/*
 * This file is generated by jOOQ.
 */
package cn.qlq.jooq.tables.interfaces;


import javax.annotation.Generated;
import java.io.Serializable;
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
public interface IUser extends Serializable {

    /**
     * Setter for <code>test1.user.id</code>.
     */
    public void setId(Integer value);

    /**
     * Getter for <code>test1.user.id</code>.
     */
    public Integer getId();

    /**
     * Setter for <code>test1.user.username</code>.
     */
    public void setUsername(String value);

    /**
     * Getter for <code>test1.user.username</code>.
     */
    public String getUsername();

    /**
     * Setter for <code>test1.user.password</code>.
     */
    public void setPassword(String value);

    /**
     * Getter for <code>test1.user.password</code>.
     */
    public String getPassword();

    /**
     * Setter for <code>test1.user.userfullname</code>.
     */
    public void setUserfullname(String value);

    /**
     * Getter for <code>test1.user.userfullname</code>.
     */
    public String getUserfullname();

    /**
     * Setter for <code>test1.user.createtime</code>.
     */
    public void setCreatetime(Date value);

    /**
     * Getter for <code>test1.user.createtime</code>.
     */
    public Date getCreatetime();

    /**
     * Setter for <code>test1.user.isdeleted</code>.
     */
    public void setIsdeleted(String value);

    /**
     * Getter for <code>test1.user.isdeleted</code>.
     */
    public String getIsdeleted();

    /**
     * Setter for <code>test1.user.sex</code>.
     */
    public void setSex(String value);

    /**
     * Getter for <code>test1.user.sex</code>.
     */
    public String getSex();

    /**
     * Setter for <code>test1.user.address</code>.
     */
    public void setAddress(String value);

    /**
     * Getter for <code>test1.user.address</code>.
     */
    public String getAddress();

    /**
     * Setter for <code>test1.user.roles</code>.
     */
    public void setRoles(String value);

    /**
     * Getter for <code>test1.user.roles</code>.
     */
    public String getRoles();

    /**
     * Setter for <code>test1.user.userblank</code>.
     */
    public void setUserblank(String value);

    /**
     * Getter for <code>test1.user.userblank</code>.
     */
    public String getUserblank();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IUser
     */
    public void from(cn.qlq.jooq.tables.interfaces.IUser from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IUser
     */
    public <E extends cn.qlq.jooq.tables.interfaces.IUser> E into(E into);
}
