package com.xiaoshu.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Attachment implements Serializable {
    /**
     * 附件ID
     */
    @Id
    @Column(name = "attachmentId")
    private Integer attachmentid;

    /**
     * 名称
     */
    @Column(name = "attachmentName")
    private String attachmentname;

    /**
     * 路径
     */
    @Column(name = "attachmentPath")
    private String attachmentpath;

    /**
     * 时间
     */
    @Column(name = "attachmentTime")
    private Date attachmenttime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取附件ID
     *
     * @return attachmentId - 附件ID
     */
    public Integer getAttachmentid() {
        return attachmentid;
    }

    /**
     * 设置附件ID
     *
     * @param attachmentid 附件ID
     */
    public void setAttachmentid(Integer attachmentid) {
        this.attachmentid = attachmentid;
    }

    /**
     * 获取名称
     *
     * @return attachmentName - 名称
     */
    public String getAttachmentname() {
        return attachmentname;
    }

    /**
     * 设置名称
     *
     * @param attachmentname 名称
     */
    public void setAttachmentname(String attachmentname) {
        this.attachmentname = attachmentname == null ? null : attachmentname.trim();
    }

    /**
     * 获取路径
     *
     * @return attachmentPath - 路径
     */
    public String getAttachmentpath() {
        return attachmentpath;
    }

    /**
     * 设置路径
     *
     * @param attachmentpath 路径
     */
    public void setAttachmentpath(String attachmentpath) {
        this.attachmentpath = attachmentpath == null ? null : attachmentpath.trim();
    }

    /**
     * 获取时间
     *
     * @return attachmentTime - 时间
     */
    public Date getAttachmenttime() {
        return attachmenttime;
    }

    /**
     * 设置时间
     *
     * @param attachmenttime 时间
     */
    public void setAttachmenttime(Date attachmenttime) {
        this.attachmenttime = attachmenttime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", attachmentid=").append(attachmentid);
        sb.append(", attachmentname=").append(attachmentname);
        sb.append(", attachmentpath=").append(attachmentpath);
        sb.append(", attachmenttime=").append(attachmenttime);
        sb.append("]");
        return sb.toString();
    }
}