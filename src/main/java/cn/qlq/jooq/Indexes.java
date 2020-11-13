/*
 * This file is generated by jOOQ.
 */
package cn.qlq.jooq;


import cn.qlq.jooq.tables.COUNTRY;
import cn.qlq.jooq.tables.FLYWAY_SCHEMA_HISTORY;
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

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>test1</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index COUNTRY_PRIMARY = Indexes0.COUNTRY_PRIMARY;
    public static final Index FLYWAY_SCHEMA_HISTORY_FLYWAY_SCHEMA_HISTORY_S_IDX = Indexes0.FLYWAY_SCHEMA_HISTORY_FLYWAY_SCHEMA_HISTORY_S_IDX;
    public static final Index FLYWAY_SCHEMA_HISTORY_PRIMARY = Indexes0.FLYWAY_SCHEMA_HISTORY_PRIMARY;
    public static final Index MP_USER_PRIMARY = Indexes0.MP_USER_PRIMARY;
    public static final Index SYSTEM_SHIRO_PERMISSION_PRIMARY = Indexes0.SYSTEM_SHIRO_PERMISSION_PRIMARY;
    public static final Index SYSTEM_SHIRO_ROLE_PRIMARY = Indexes0.SYSTEM_SHIRO_ROLE_PRIMARY;
    public static final Index SYSTEM_SHIRO_ROLE_PERMISSIONS_FKID5G3KH3JHOJ05A1JBT3GWXE9 = Indexes0.SYSTEM_SHIRO_ROLE_PERMISSIONS_FKID5G3KH3JHOJ05A1JBT3GWXE9;
    public static final Index SYSTEM_SHIRO_ROLE_PERMISSIONS_UK_9BGRR9Y35K9DEH6OS7FQ2HXJC = Indexes0.SYSTEM_SHIRO_ROLE_PERMISSIONS_UK_9BGRR9Y35K9DEH6OS7FQ2HXJC;
    public static final Index SYSTEM_SHIRO_USER_PRIMARY = Indexes0.SYSTEM_SHIRO_USER_PRIMARY;
    public static final Index SYSTEM_SHIRO_USER_ROLES_FKJ4GCMLP5WM0FAPX9VUUOIS1MF = Indexes0.SYSTEM_SHIRO_USER_ROLES_FKJ4GCMLP5WM0FAPX9VUUOIS1MF;
    public static final Index SYSTEM_SHIRO_USER_ROLES_UK_I8LLCNOHDPE9U5XFNBA0G4JQY = Indexes0.SYSTEM_SHIRO_USER_ROLES_UK_I8LLCNOHDPE9U5XFNBA0G4JQY;
    public static final Index USER_PRIMARY = Indexes0.USER_PRIMARY;
    public static final Index USER2_FK7PBUV6U730MARAX4LERUMGY4S = Indexes0.USER2_FK7PBUV6U730MARAX4LERUMGY4S;
    public static final Index USER2_PRIMARY = Indexes0.USER2_PRIMARY;
    public static final Index USER_COUNTRY_PRIMARY = Indexes0.USER_COUNTRY_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index COUNTRY_PRIMARY = Internal.createIndex("PRIMARY", COUNTRY.COUNTRY, new OrderField[] { COUNTRY.COUNTRY.ID }, true);
        public static Index FLYWAY_SCHEMA_HISTORY_FLYWAY_SCHEMA_HISTORY_S_IDX = Internal.createIndex("flyway_schema_history_s_idx", FLYWAY_SCHEMA_HISTORY.FLYWAY_SCHEMA_HISTORY, new OrderField[] { FLYWAY_SCHEMA_HISTORY.FLYWAY_SCHEMA_HISTORY.SUCCESS }, false);
        public static Index FLYWAY_SCHEMA_HISTORY_PRIMARY = Internal.createIndex("PRIMARY", FLYWAY_SCHEMA_HISTORY.FLYWAY_SCHEMA_HISTORY, new OrderField[] { FLYWAY_SCHEMA_HISTORY.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK }, true);
        public static Index MP_USER_PRIMARY = Internal.createIndex("PRIMARY", MP_USER.MP_USER, new OrderField[] { MP_USER.MP_USER.ID }, true);
        public static Index SYSTEM_SHIRO_PERMISSION_PRIMARY = Internal.createIndex("PRIMARY", SYSTEM_SHIRO_PERMISSION.SYSTEM_SHIRO_PERMISSION, new OrderField[] { SYSTEM_SHIRO_PERMISSION.SYSTEM_SHIRO_PERMISSION.ID }, true);
        public static Index SYSTEM_SHIRO_ROLE_PRIMARY = Internal.createIndex("PRIMARY", SYSTEM_SHIRO_ROLE.SYSTEM_SHIRO_ROLE, new OrderField[] { SYSTEM_SHIRO_ROLE.SYSTEM_SHIRO_ROLE.ID }, true);
        public static Index SYSTEM_SHIRO_ROLE_PERMISSIONS_FKID5G3KH3JHOJ05A1JBT3GWXE9 = Internal.createIndex("FKid5g3kh3jhoj05a1jbt3gwxe9", SYSTEM_SHIRO_ROLE_PERMISSIONS.SYSTEM_SHIRO_ROLE_PERMISSIONS, new OrderField[] { SYSTEM_SHIRO_ROLE_PERMISSIONS.SYSTEM_SHIRO_ROLE_PERMISSIONS.ROLE_ID }, false);
        public static Index SYSTEM_SHIRO_ROLE_PERMISSIONS_UK_9BGRR9Y35K9DEH6OS7FQ2HXJC = Internal.createIndex("UK_9bgrr9y35k9deh6os7fq2hxjc", SYSTEM_SHIRO_ROLE_PERMISSIONS.SYSTEM_SHIRO_ROLE_PERMISSIONS, new OrderField[] { SYSTEM_SHIRO_ROLE_PERMISSIONS.SYSTEM_SHIRO_ROLE_PERMISSIONS.PERMISSIONS_ID }, true);
        public static Index SYSTEM_SHIRO_USER_PRIMARY = Internal.createIndex("PRIMARY", SYSTEM_SHIRO_USER.SYSTEM_SHIRO_USER, new OrderField[] { SYSTEM_SHIRO_USER.SYSTEM_SHIRO_USER.ID }, true);
        public static Index SYSTEM_SHIRO_USER_ROLES_FKJ4GCMLP5WM0FAPX9VUUOIS1MF = Internal.createIndex("FKj4gcmlp5wm0fapx9vuuois1mf", SYSTEM_SHIRO_USER_ROLES.SYSTEM_SHIRO_USER_ROLES, new OrderField[] { SYSTEM_SHIRO_USER_ROLES.SYSTEM_SHIRO_USER_ROLES.SHIRO_USER_ID }, false);
        public static Index SYSTEM_SHIRO_USER_ROLES_UK_I8LLCNOHDPE9U5XFNBA0G4JQY = Internal.createIndex("UK_i8llcnohdpe9u5xfnba0g4jqy", SYSTEM_SHIRO_USER_ROLES.SYSTEM_SHIRO_USER_ROLES, new OrderField[] { SYSTEM_SHIRO_USER_ROLES.SYSTEM_SHIRO_USER_ROLES.ROLES_ID }, true);
        public static Index USER_PRIMARY = Internal.createIndex("PRIMARY", USER.USER, new OrderField[] { USER.USER.ID }, true);
        public static Index USER2_FK7PBUV6U730MARAX4LERUMGY4S = Internal.createIndex("FK7pbuv6u730marax4lerumgy4s", USER2.USER2, new OrderField[] { USER2.USER2.COUNTRY_ID }, false);
        public static Index USER2_PRIMARY = Internal.createIndex("PRIMARY", USER2.USER2, new OrderField[] { USER2.USER2.ID }, true);
        public static Index USER_COUNTRY_PRIMARY = Internal.createIndex("PRIMARY", USER_COUNTRY.USER_COUNTRY, new OrderField[] { USER_COUNTRY.USER_COUNTRY.USERID, USER_COUNTRY.USER_COUNTRY.COUNTRYID }, true);
    }
}
