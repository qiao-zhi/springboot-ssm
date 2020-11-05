/*
 * This file is generated by jOOQ.
 */
package cn.qlq.jooq.tables.pojos;


import cn.qlq.jooq.tables.interfaces.ISystemShiroUserRoles;

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
public class SystemShiroUserRoles implements ISystemShiroUserRoles {

    private static final long serialVersionUID = 1856995022;

    private Integer shiroUserId;
    private Integer rolesId;

    public SystemShiroUserRoles() {}

    public SystemShiroUserRoles(ISystemShiroUserRoles value) {
        this.shiroUserId = value.getShiroUserId();
        this.rolesId = value.getRolesId();
    }

    public SystemShiroUserRoles(
        Integer shiroUserId,
        Integer rolesId
    ) {
        this.shiroUserId = shiroUserId;
        this.rolesId = rolesId;
    }

    @Override
    public Integer getShiroUserId() {
        return this.shiroUserId;
    }

    @Override
    public void setShiroUserId(Integer shiroUserId) {
        this.shiroUserId = shiroUserId;
    }

    @Override
    public Integer getRolesId() {
        return this.rolesId;
    }

    @Override
    public void setRolesId(Integer rolesId) {
        this.rolesId = rolesId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SystemShiroUserRoles (");

        sb.append(shiroUserId);
        sb.append(", ").append(rolesId);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ISystemShiroUserRoles from) {
        setShiroUserId(from.getShiroUserId());
        setRolesId(from.getRolesId());
    }

    @Override
    public <E extends ISystemShiroUserRoles> E into(E into) {
        into.from(this);
        return into;
    }
}