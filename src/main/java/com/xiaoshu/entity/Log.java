package com.xiaoshu.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Log implements Serializable {
    /**
     * 日志
     */
    @Id
    @Column(name = "logId")
    private Long logid;

    /**
     * 操作人
     */
    @Column(name = "userName")
    private String username;

    /**
     * 时间
     */
    @Column(name = "createTime")
    private Date createtime;

    /**
     * 操作类型（增删改）
     */
    private String operation;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 详细
     */
    private String content;

    private static final long serialVersionUID = 1L;

    /**
     * 获取日志
     *
     * @return logId - 日志
     */
    public Long getLogid() {
        return logid;
    }

    /**
     * 设置日志
     *
     * @param logid 日志
     */
    public void setLogid(Long logid) {
        this.logid = logid;
    }

    /**
     * 获取操作人
     *
     * @return userName - 操作人
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置操作人
     *
     * @param username 操作人
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取时间
     *
     * @return createTime - 时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置时间
     *
     * @param createtime 时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取操作类型（增删改）
     *
     * @return operation - 操作类型（增删改）
     */
    public String getOperation() {
        return operation;
    }

    /**
     * 设置操作类型（增删改）
     *
     * @param operation 操作类型（增删改）
     */
    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    /**
     * 获取IP地址
     *
     * @return ip - IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置IP地址
     *
     * @param ip IP地址
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 获取所属模块
     *
     * @return module - 所属模块
     */
    public String getModule() {
        return module;
    }

    /**
     * 设置所属模块
     *
     * @param module 所属模块
     */
    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    /**
     * 获取详细
     *
     * @return content - 详细
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置详细
     *
     * @param content 详细
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logid=").append(logid);
        sb.append(", username=").append(username);
        sb.append(", createtime=").append(createtime);
        sb.append(", operation=").append(operation);
        sb.append(", ip=").append(ip);
        sb.append(", module=").append(module);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}