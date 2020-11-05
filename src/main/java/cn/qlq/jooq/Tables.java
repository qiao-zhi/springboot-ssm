/*
 * This file is generated by jOOQ.
 */
package cn.qlq.jooq;


import cn.qlq.jooq.tables.COUNTRY;
import cn.qlq.jooq.tables.HIBERNATE_SEQUENCE;
import cn.qlq.jooq.tables.MP_USER;
import cn.qlq.jooq.tables.SYSTEM_SHIRO_PERMISSION;
import cn.qlq.jooq.tables.SYSTEM_SHIRO_ROLE;
import cn.qlq.jooq.tables.SYSTEM_SHIRO_ROLE_PERMISSIONS;
import cn.qlq.jooq.tables.SYSTEM_SHIRO_USER;
import cn.qlq.jooq.tables.SYSTEM_SHIRO_USER_ROLES;
import cn.qlq.jooq.tables.USER;
import cn.qlq.jooq.tables.USER2;
import cn.qlq.jooq.tables.USER_COUNTRY;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in test1
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>test1.country</code>.
     */
    public static final COUNTRY COUNTRY = cn.qlq.jooq.tables.COUNTRY.COUNTRY;

    /**
     * The table <code>test1.hibernate_sequence</code>.
     */
    public static final HIBERNATE_SEQUENCE HIBERNATE_SEQUENCE = cn.qlq.jooq.tables.HIBERNATE_SEQUENCE.HIBERNATE_SEQUENCE;

    /**
     * The table <code>test1.mp_user</code>.
     */
    public static final MP_USER MP_USER = cn.qlq.jooq.tables.MP_USER.MP_USER;

    /**
     * The table <code>test1.system_shiro_permission</code>.
     */
    public static final SYSTEM_SHIRO_PERMISSION SYSTEM_SHIRO_PERMISSION = cn.qlq.jooq.tables.SYSTEM_SHIRO_PERMISSION.SYSTEM_SHIRO_PERMISSION;

    /**
     * The table <code>test1.system_shiro_role</code>.
     */
    public static final SYSTEM_SHIRO_ROLE SYSTEM_SHIRO_ROLE = cn.qlq.jooq.tables.SYSTEM_SHIRO_ROLE.SYSTEM_SHIRO_ROLE;

    /**
     * The table <code>test1.system_shiro_role_permissions</code>.
     */
    public static final SYSTEM_SHIRO_ROLE_PERMISSIONS SYSTEM_SHIRO_ROLE_PERMISSIONS = cn.qlq.jooq.tables.SYSTEM_SHIRO_ROLE_PERMISSIONS.SYSTEM_SHIRO_ROLE_PERMISSIONS;

    /**
     * The table <code>test1.system_shiro_user</code>.
     */
    public static final SYSTEM_SHIRO_USER SYSTEM_SHIRO_USER = cn.qlq.jooq.tables.SYSTEM_SHIRO_USER.SYSTEM_SHIRO_USER;

    /**
     * The table <code>test1.system_shiro_user_roles</code>.
     */
    public static final SYSTEM_SHIRO_USER_ROLES SYSTEM_SHIRO_USER_ROLES = cn.qlq.jooq.tables.SYSTEM_SHIRO_USER_ROLES.SYSTEM_SHIRO_USER_ROLES;

    /**
     * The table <code>test1.user</code>.
     */
    public static final USER USER = cn.qlq.jooq.tables.USER.USER;

    /**
     * The table <code>test1.user2</code>.
     */
    public static final USER2 USER2 = cn.qlq.jooq.tables.USER2.USER2;

    /**
     * The table <code>test1.user_country</code>.
     */
    public static final USER_COUNTRY USER_COUNTRY = cn.qlq.jooq.tables.USER_COUNTRY.USER_COUNTRY;
}
