package com.xiaoshu.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

public class Token implements Serializable {
    @Id
    @Column(name = "tokenId")
    private Long tokenid;

    /**
     * 用户ID
     */
    @Column(name = "userId")
    private Integer userid;

    /**
     * 用户（md5）
     */
    @Column(name = "userAgent")
    private String useragent;

    /**
     * md5(username+md5(useragent))
     */
    private String token;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createtime;

    /**
     * 到期时间
     */
    @Column(name = "expireTime")
    private Date expiretime;

    private static final long serialVersionUID = 1L;

    /**
     * @return tokenId
     */
    public Long getTokenid() {
        return tokenid;
    }

    /**
     * @param tokenid
     */
    public void setTokenid(Long tokenid) {
        this.tokenid = tokenid;
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
     * 获取用户（md5）
     *
     * @return userAgent - 用户（md5）
     */
    public String getUseragent() {
        return useragent;
    }

    /**
     * 设置用户（md5）
     *
     * @param useragent 用户（md5）
     */
    public void setUseragent(String useragent) {
        this.useragent = useragent == null ? null : useragent.trim();
    }

    /**
     * 获取md5(username+md5(useragent))
     *
     * @return token - md5(username+md5(useragent))
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置md5(username+md5(useragent))
     *
     * @param token md5(username+md5(useragent))
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * 获取创建时间
     *
     * @return createTime - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime == null ? new Date() : createtime;
    }

    /**
     * 获取到期时间
     *
     * @return expireTime - 到期时间
     */
    public Date getExpiretime() {
        return expiretime;
    }

    /**
     * 设置到期时间
     *
     * @param expiretime 到期时间
     */
    public void setExpiretime(Date expiretime) {
        this.expiretime = expiretime == null ? new Date() : expiretime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tokenid=").append(tokenid);
        sb.append(", userid=").append(userid);
        sb.append(", useragent=").append(useragent);
        sb.append(", token=").append(token);
        sb.append(", createtime=").append(createtime);
        sb.append(", expiretime=").append(expiretime);
        sb.append("]");
        return sb.toString();
    }
}