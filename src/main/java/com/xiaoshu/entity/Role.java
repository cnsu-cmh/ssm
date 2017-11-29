package com.xiaoshu.entity;

import java.io.Serializable;
import javax.persistence.*;

public class Role implements Serializable {
    /**
     * 角色ID
     */
    @Id
    @Column(name = "roleId")
    private Integer roleid;

    /**
     * 角色名称
     */
    @Column(name = "roleName")
    private String rolename;

    /**
     * 菜单IDs
     */
    @Column(name = "menuIds")
    private String menuids;

    /**
     * 按钮IDS
     */
    @Column(name = "operationIds")
    private String operationids;

    /**
     * 描述
     */
    @Column(name = "roleDescription")
    private String roledescription;
    
    private static final long serialVersionUID = 1L;

    /**
     * 获取角色ID
     *
     * @return roleId - 角色ID
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * 设置角色ID
     *
     * @param roleid 角色ID
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * 获取角色名称
     *
     * @return roleName - 角色名称
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * 设置角色名称
     *
     * @param rolename 角色名称
     */
    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    /**
     * 获取菜单IDs
     *
     * @return menuIds - 菜单IDs
     */
    public String getMenuids() {
        return menuids;
    }

    /**
     * 设置菜单IDs
     *
     * @param menuids 菜单IDs
     */
    public void setMenuids(String menuids) {
        this.menuids = menuids == null ? null : menuids.trim();
    }

    /**
     * 获取按钮IDS
     *
     * @return operationIds - 按钮IDS
     */
    public String getOperationids() {
        return operationids;
    }

    /**
     * 设置按钮IDS
     *
     * @param operationids 按钮IDS
     */
    public void setOperationids(String operationids) {
        this.operationids = operationids == null ? null : operationids.trim();
    }

    /**
     * 获取描述
     *
     * @return roleDescription - 描述
     */
    public String getRoledescription() {
        return roledescription;
    }

    /**
     * 设置描述
     *
     * @param roledescription 描述
     */
    public void setRoledescription(String roledescription) {
        this.roledescription = roledescription == null ? null : roledescription.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleid=").append(roleid);
        sb.append(", rolename=").append(rolename);
        sb.append(", menuids=").append(menuids);
        sb.append(", operationids=").append(operationids);
        sb.append(", roledescription=").append(roledescription);
        sb.append("]");
        return sb.toString();
    }
}