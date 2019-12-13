package com.ztkj.sys.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Description: 日志类，对应t_log
 * @Author: HuangKL
 * @Date: 2019/11/20 16:53
 */
public class Log {

    private int id;

    private User userId;

    private String moduleName;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public Log() {
    }

    public Log(User userId, String moduleName, String content) {
        this.userId = userId;
        Module module = new Module();
        this.moduleName = moduleName;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

