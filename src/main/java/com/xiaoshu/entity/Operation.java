package com.xiaoshu.entity;

import java.io.Serializable;
import javax.persistence.*;

public class Operation implements Serializable {
    /**
     * 具体的方法
     */
    @Id
    @Column(name = "operationId")
    private Integer operationid;

    /**
     * 方法名
     */
    @Column(name = "operationName")
    private String operationname;

    /**
     * 所属菜单
     */
    @Column(name = "menuId")
    private Integer menuid;

    @Column(name = "menuName")
    private String menuname;
    
    @Column(name = "iconCls")
    private String iconcls;
    
    @Column(name = "operationCode")
    private String operationcode;

    private static final long serialVersionUID = 1L;

    /**
     * 获取具体的方法
     *
     * @return operationId - 具体的方法
     */
    public Integer getOperationid() {
        return operationid;
    }

    /**
     * 设置具体的方法
     *
     * @param operationid 具体的方法
     */
    public void setOperationid(Integer operationid) {
        this.operationid = operationid;
    }

    /**
     * 获取方法名
     *
     * @return operationName - 方法名
     */
    public String getOperationname() {
        return operationname;
    }

    /**
     * 设置方法名
     *
     * @param operationname 方法名
     */
    public void setOperationname(String operationname) {
        this.operationname = operationname == null ? null : operationname.trim();
    }

    /**
     * 获取所属菜单
     *
     * @return menuId - 所属菜单
     */
    public Integer getMenuid() {
        return menuid;
    }

    /**
     * 设置所属菜单
     *
     * @param menuid 所属菜单
     */
    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    /**
     * @return menuName
     */
    public String getMenuname() {
        return menuname;
    }

    /**
     * @param menuname
     */
    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }
    
    

    public String getIconcls() {
		return iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}
	
	public String getOperationcode() {
		return operationcode;
	}

	public void setOperationcode(String operationcode) {
		this.operationcode = operationcode;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", operationid=").append(operationid);
        sb.append(", operationname=").append(operationname);
        sb.append(", menuid=").append(menuid);
        sb.append(", menuname=").append(menuname);
        sb.append("]");
        return sb.toString();
    }
}