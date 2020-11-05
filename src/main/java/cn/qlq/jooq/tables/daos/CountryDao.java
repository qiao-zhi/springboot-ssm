/*
 * This file is generated by jOOQ.
 */
package cn.qlq.jooq.tables.daos;


import cn.qlq.jooq.tables.COUNTRY;
import cn.qlq.jooq.tables.pojos.Country;
import cn.qlq.jooq.tables.records.CountryRecord;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.springframework.stereotype.Repository;


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
@Repository
public class CountryDao extends DAOImpl<CountryRecord, Country, Integer> {

    /**
     * Create a new CountryDao without any configuration
     */
    public CountryDao() {
        super(COUNTRY.COUNTRY, Country.class);
    }

    /**
     * Create a new CountryDao with an attached configuration
     */
    public CountryDao(Configuration configuration) {
        super(COUNTRY.COUNTRY, Country.class, configuration);
    }

    @Override
    public Integer getId(Country object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<Country> fetchRangeOfId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(COUNTRY.COUNTRY.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<Country> fetchById(Integer... values) {
        return fetch(COUNTRY.COUNTRY.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Country fetchOneById(Integer value) {
        return fetchOne(COUNTRY.COUNTRY.ID, value);
    }

    /**
     * Fetch records that have <code>countryname BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<Country> fetchRangeOfCountryname(String lowerInclusive, String upperInclusive) {
        return fetchRange(COUNTRY.COUNTRY.COUNTRYNAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>countryname IN (values)</code>
     */
    public List<Country> fetchByCountryname(String... values) {
        return fetch(COUNTRY.COUNTRY.COUNTRYNAME, values);
    }
}
