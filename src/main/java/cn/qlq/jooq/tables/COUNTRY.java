/*
 * This file is generated by jOOQ.
 */
package cn.qlq.jooq.tables;


import cn.qlq.jooq.Indexes;
import cn.qlq.jooq.Keys;
import cn.qlq.jooq.Test1;
import cn.qlq.jooq.tables.records.CountryRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import javax.annotation.Generated;
import java.util.Arrays;
import java.util.List;


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
public class COUNTRY extends TableImpl<CountryRecord> {

    private static final long serialVersionUID = -337812500;

    /**
     * The reference instance of <code>test1.country</code>
     */
    public static final cn.qlq.jooq.tables.COUNTRY COUNTRY = new COUNTRY();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CountryRecord> getRecordType() {
        return CountryRecord.class;
    }

    /**
     * The column <code>test1.country.id</code>.
     */
    public final TableField<CountryRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>test1.country.countryname</code>.
     */
    public final TableField<CountryRecord, String> COUNTRYNAME = createField(DSL.name("countryname"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * Create a <code>test1.country</code> table reference
     */
    public COUNTRY() {
        this(DSL.name("country"), null);
    }

    /**
     * Create an aliased <code>test1.country</code> table reference
     */
    public COUNTRY(String alias) {
        this(DSL.name(alias), COUNTRY);
    }

    /**
     * Create an aliased <code>test1.country</code> table reference
     */
    public COUNTRY(Name alias) {
        this(alias, COUNTRY);
    }

    private COUNTRY(Name alias, Table<CountryRecord> aliased) {
        this(alias, aliased, null);
    }

    private COUNTRY(Name alias, Table<CountryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> COUNTRY(Table<O> child, ForeignKey<O, CountryRecord> key) {
        super(child, key, COUNTRY);
    }

    @Override
    public Schema getSchema() {
        return Test1.TEST1;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.COUNTRY_PRIMARY);
    }

    @Override
    public Identity<CountryRecord, Integer> getIdentity() {
        return Keys.IDENTITY_COUNTRY;
    }

    @Override
    public UniqueKey<CountryRecord> getPrimaryKey() {
        return Keys.KEY_COUNTRY_PRIMARY;
    }

    @Override
    public List<UniqueKey<CountryRecord>> getKeys() {
        return Arrays.<UniqueKey<CountryRecord>>asList(Keys.KEY_COUNTRY_PRIMARY);
    }

    @Override
    public cn.qlq.jooq.tables.COUNTRY as(String alias) {
        return new COUNTRY(DSL.name(alias), this);
    }

    @Override
    public cn.qlq.jooq.tables.COUNTRY as(Name alias) {
        return new COUNTRY(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public cn.qlq.jooq.tables.COUNTRY rename(String name) {
        return new COUNTRY(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public cn.qlq.jooq.tables.COUNTRY rename(Name name) {
        return new COUNTRY(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
