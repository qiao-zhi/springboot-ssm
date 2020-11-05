/*
 * This file is generated by jOOQ.
 */
package cn.qlq.jooq.tables;


import cn.qlq.jooq.Indexes;
import cn.qlq.jooq.Keys;
import cn.qlq.jooq.Test1;
import cn.qlq.jooq.tables.records.SystemShiroRoleRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class SYSTEM_SHIRO_ROLE extends TableImpl<SystemShiroRoleRecord> {

    private static final long serialVersionUID = -1781487373;

    /**
     * The reference instance of <code>test1.system_shiro_role</code>
     */
    public static final SYSTEM_SHIRO_ROLE SYSTEM_SHIRO_ROLE = new SYSTEM_SHIRO_ROLE();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SystemShiroRoleRecord> getRecordType() {
        return SystemShiroRoleRecord.class;
    }

    /**
     * The column <code>test1.system_shiro_role.id</code>.
     */
    public final TableField<SystemShiroRoleRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>test1.system_shiro_role.description</code>.
     */
    public final TableField<SystemShiroRoleRecord, String> DESCRIPTION = createField(DSL.name("description"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>test1.system_shiro_role.name</code>.
     */
    public final TableField<SystemShiroRoleRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * Create a <code>test1.system_shiro_role</code> table reference
     */
    public SYSTEM_SHIRO_ROLE() {
        this(DSL.name("system_shiro_role"), null);
    }

    /**
     * Create an aliased <code>test1.system_shiro_role</code> table reference
     */
    public SYSTEM_SHIRO_ROLE(String alias) {
        this(DSL.name(alias), SYSTEM_SHIRO_ROLE);
    }

    /**
     * Create an aliased <code>test1.system_shiro_role</code> table reference
     */
    public SYSTEM_SHIRO_ROLE(Name alias) {
        this(alias, SYSTEM_SHIRO_ROLE);
    }

    private SYSTEM_SHIRO_ROLE(Name alias, Table<SystemShiroRoleRecord> aliased) {
        this(alias, aliased, null);
    }

    private SYSTEM_SHIRO_ROLE(Name alias, Table<SystemShiroRoleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> SYSTEM_SHIRO_ROLE(Table<O> child, ForeignKey<O, SystemShiroRoleRecord> key) {
        super(child, key, SYSTEM_SHIRO_ROLE);
    }

    @Override
    public Schema getSchema() {
        return Test1.TEST1;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.SYSTEM_SHIRO_ROLE_PRIMARY);
    }

    @Override
    public UniqueKey<SystemShiroRoleRecord> getPrimaryKey() {
        return Keys.KEY_SYSTEM_SHIRO_ROLE_PRIMARY;
    }

    @Override
    public List<UniqueKey<SystemShiroRoleRecord>> getKeys() {
        return Arrays.<UniqueKey<SystemShiroRoleRecord>>asList(Keys.KEY_SYSTEM_SHIRO_ROLE_PRIMARY);
    }

    @Override
    public SYSTEM_SHIRO_ROLE as(String alias) {
        return new SYSTEM_SHIRO_ROLE(DSL.name(alias), this);
    }

    @Override
    public SYSTEM_SHIRO_ROLE as(Name alias) {
        return new SYSTEM_SHIRO_ROLE(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SYSTEM_SHIRO_ROLE rename(String name) {
        return new SYSTEM_SHIRO_ROLE(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SYSTEM_SHIRO_ROLE rename(Name name) {
        return new SYSTEM_SHIRO_ROLE(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}