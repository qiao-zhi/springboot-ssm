/*
 * This file is generated by jOOQ.
 */
package cn.qlq.jooq.tables.pojos;


import cn.qlq.jooq.tables.interfaces.ISystemShiroRole;

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
public class SystemShiroRole implements ISystemShiroRole {

    private static final long serialVersionUID = -1108503897;

    private Integer id;
    private String  description;
    private String  name;

    public SystemShiroRole() {}

    public SystemShiroRole(ISystemShiroRole value) {
        this.id = value.getId();
        this.description = value.getDescription();
        this.name = value.getName();
    }

    public SystemShiroRole(
        Integer id,
        String  description,
        String  name
    ) {
        this.id = id;
        this.description = description;
        this.name = name;
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
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
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
    public String toString() {
        StringBuilder sb = new StringBuilder("SystemShiroRole (");

        sb.append(id);
        sb.append(", ").append(description);
        sb.append(", ").append(name);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ISystemShiroRole from) {
        setId(from.getId());
        setDescription(from.getDescription());
        setName(from.getName());
    }

    @Override
    public <E extends ISystemShiroRole> E into(E into) {
        into.from(this);
        return into;
    }
}