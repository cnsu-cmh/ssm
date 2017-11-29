package com.xiaoshu.entity;

import java.io.Serializable;
import javax.persistence.*;

public class Menu implements Serializable {
    /**
     * 菜单ID
     */
    @Id
    @Column(name = "menuId")
    private Integer menuid;

    /**
     * 名称
     */
    @Column(name = "menuName")
    private String menuname;

    /**
     * 方法
     */
    @Column(name = "menuUrl")
    private String menuurl;

    /**
     * 父ID
     */
    @Column(name = "parentId")
    private Integer parentid;

    /**
     * 描述
     */
    @Column(name = "menuDescription")
    private String menudescription;

    /**
     * 状态/OPEN/CLOSED
     */
    private String state;

    /**
     * 图标
     */
    @Column(name = "iconCls")
    private String iconcls;

    /**
     * 顺序排序
     */
    private Integer seq;

    private static final long serialVersionUID = 1L;

    /**
     * 获取菜单ID
     *
     * @return menuId - 菜单ID
     */
    public Integer getMenuid() {
        return menuid;
    }

    /**
     * 设置菜单ID
     *
     * @param menuid 菜单ID
     */
    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    /**
     * 获取名称
     *
     * @return menuName - 名称
     */
    public String getMenuname() {
        return menuname;
    }

    /**
     * 设置名称
     *
     * @param menuname 名称
     */
    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    /**
     * 获取方法
     *
     * @return menuUrl - 方法
     */
    public String getMenuurl() {
        return menuurl;
    }

    /**
     * 设置方法
     *
     * @param menuurl 方法
     */
    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl == null ? null : menuurl.trim();
    }

    /**
     * 获取父ID
     *
     * @return parentId - 父ID
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * 设置父ID
     *
     * @param parentid 父ID
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * 获取描述
     *
     * @return menuDescription - 描述
     */
    public String getMenudescription() {
        return menudescription;
    }

    /**
     * 设置描述
     *
     * @param menudescription 描述
     */
    public void setMenudescription(String menudescription) {
        this.menudescription = menudescription == null ? null : menudescription.trim();
    }

    /**
     * 获取状态/OPEN/CLOSED
     *
     * @return state - 状态/OPEN/CLOSED
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态/OPEN/CLOSED
     *
     * @param state 状态/OPEN/CLOSED
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * 获取图标
     *
     * @return iconCls - 图标
     */
    public String getIconcls() {
        return iconcls;
    }

    /**
     * 设置图标
     *
     * @param iconcls 图标
     */
    public void setIconcls(String iconcls) {
        this.iconcls = iconcls == null ? null : iconcls.trim();
    }

    /**
     * 获取顺序排序
     *
     * @return seq - 顺序排序
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置顺序排序
     *
     * @param seq 顺序排序
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    
    

    public Menu() {
	}

	public Menu(Integer menuid, String menuname, String menuurl, Integer parentid, String menudescription, String state,
			String iconcls, Integer seq) {
		super();
		this.menuid = menuid;
		this.menuname = menuname;
		this.menuurl = menuurl;
		this.parentid = parentid;
		this.menudescription = menudescription;
		this.state = state;
		this.iconcls = iconcls;
		this.seq = seq;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuid=").append(menuid);
        sb.append(", menuname=").append(menuname);
        sb.append(", menuurl=").append(menuurl);
        sb.append(", parentid=").append(parentid);
        sb.append(", menudescription=").append(menudescription);
        sb.append(", state=").append(state);
        sb.append(", iconcls=").append(iconcls);
        sb.append(", seq=").append(seq);
        sb.append("]");
        return sb.toString();
    }
}