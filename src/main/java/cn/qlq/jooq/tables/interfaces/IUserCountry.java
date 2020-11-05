/*
 * This file is generated by jOOQ.
 */
package cn.qlq.jooq.tables.interfaces;


import java.io.Serializable;

import javax.annotation.Generated;


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
public interface IUserCountry extends Serializable {

    /**
     * Setter for <code>test1.user_country.userId</code>.
     */
    public void setUserid(Integer value);

    /**
     * Getter for <code>test1.user_country.userId</code>.
     */
    public Integer getUserid();

    /**
     * Setter for <code>test1.user_country.countryId</code>.
     */
    public void setCountryid(Integer value);

    /**
     * Getter for <code>test1.user_country.countryId</code>.
     */
    public Integer getCountryid();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IUserCountry
     */
    public void from(cn.qlq.jooq.tables.interfaces.IUserCountry from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IUserCountry
     */
    public <E extends cn.qlq.jooq.tables.interfaces.IUserCountry> E into(E into);
}
