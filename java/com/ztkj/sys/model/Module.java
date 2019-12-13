package com.ztkj.sys.model;

/**
 * @Description: 模块类，对应t_module
 * @Author: HuangKL
 * @Date: 2019/11/20 16:47
 */
public class Module {
    private Integer id;
    private String name;
    private Module parent;
    private String url;
    private String status;

    public Module() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Module getParent() {
        return parent;
    }

    public void setParent(Module parent) {
        this.parent = parent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
