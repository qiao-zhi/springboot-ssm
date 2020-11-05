/*
 * This file is generated by jOOQ.
 */
package cn.qlq.jooq.tables.pojos;


import cn.qlq.jooq.tables.interfaces.IMpUser;

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
public class MpUser implements IMpUser {

    private static final long serialVersionUID = -119588753;

    private Long    id;
    private Integer age;
    private Integer deleted;
    private String  email;
    private String  name;
    private String  uniqueCode;

    public MpUser() {}

    public MpUser(IMpUser value) {
        this.id = value.getId();
        this.age = value.getAge();
        this.deleted = value.getDeleted();
        this.email = value.getEmail();
        this.name = value.getName();
        this.uniqueCode = value.getUniqueCode();
    }

    public MpUser(
        Long    id,
        Integer age,
        Integer deleted,
        String  email,
        String  name,
        String  uniqueCode
    ) {
        this.id = id;
        this.age = age;
        this.deleted = deleted;
        this.email = email;
        this.name = name;
        this.uniqueCode = uniqueCode;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Integer getAge() {
        return this.age;
    }

    @Override
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public Integer getDeleted() {
        return this.deleted;
    }

    @Override
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUniqueCode() {
        return this.uniqueCode;
    }

    @Override
    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MpUser (");

        sb.append(id);
        sb.append(", ").append(age);
        sb.append(", ").append(deleted);
        sb.append(", ").append(email);
        sb.append(", ").append(name);
        sb.append(", ").append(uniqueCode);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IMpUser from) {
        setId(from.getId());
        setAge(from.getAge());
        setDeleted(from.getDeleted());
        setEmail(from.getEmail());
        setName(from.getName());
        setUniqueCode(from.getUniqueCode());
    }

    @Override
    public <E extends IMpUser> E into(E into) {
        into.from(this);
        return into;
    }
}
