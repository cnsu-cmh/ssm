package com.xiaoshu.entity;

import java.io.Serializable;
import javax.persistence.*;

public class User implements Serializable {
    /**
     * 用户ID
     */
    @Id
    @Column(name = "userId")
    private Integer userid;

    /**
     * 用户名
     */
    @Column(name = "userName")
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型
     */
    @Column(name = "userType")
    private Byte usertype;

    /**
     * 角色ID
     */
    @Column(name = "roleId")
    private Integer roleid;

    /**
     * 描述信息
     */
    @Column(name = "userDescription")
    private String userdescription;

    private static final long serialVersionUID = 1L;
    
    
    @Transient
    private String roleName;
    
    @Transient
    private Role role;
    
    
    
    public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

    /**
     * 获取用户ID
     *
     * @return userId - 用户ID
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置用户ID
     *
     * @param userid 用户ID
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取用户名
     *
     * @return userName - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取用户类型
     *
     * @return userType - 用户类型
     */
    public Byte getUsertype() {
        return usertype;
    }

    /**
     * 设置用户类型
     *
     * @param usertype 用户类型
     */
    public void setUsertype(Byte usertype) {
        this.usertype = usertype;
    }

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
     * 获取描述信息
     *
     * @return userDescription - 描述信息
     */
    public String getUserdescription() {
        return userdescription;
    }

    /**
     * 设置描述信息
     *
     * @param userdescription 描述信息
     */
    public void setUserdescription(String userdescription) {
        this.userdescription = userdescription == null ? null : userdescription.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userid=").append(userid);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", usertype=").append(usertype);
        sb.append(", roleid=").append(roleid);
        sb.append(", userdescription=").append(userdescription);
        sb.append("]");
        return sb.toString();
    }
}