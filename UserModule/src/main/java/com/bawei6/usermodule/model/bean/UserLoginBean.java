package com.bawei6.usermodule.model.bean;

public class UserLoginBean {

    private String username;
    private String pwd;

    public UserLoginBean(String username, String password) {
        this.username = username;
        this.pwd = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
