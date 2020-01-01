package com.bawei6.usermodule.model.bean;

/**
 * @author 李荘
 * @time 2019-12-05
 * @desc 注册用户信息
 */
public class UserInfoBean {

    /**
     * id : 1
     * usercode : sample string 2
     * username : sample string 3
     * pwd : sample string 4
     * sex : sample string 5
     * birthday : sample string 6
     * headerimg : sample string 7
     * nick : sample string 8
     * utype : 9
     * imuseraccount : sample string 10
     * signdesc : sample string 11
     * openlocation : 12
     * openmsgalert : 13
     */

    private int id;
    private String usercode;
    private String username;
    private String pwd;
    private String sex;
    private String birthday;
    private String headerimg;
    private String nick;
    private int utype;
    private String imuseraccount;
    private String signdesc;
    private int openlocation;
    private int openmsgalert;

    public UserInfoBean(int id, String usercode, String username, String pwd, String sex, String birthday, String headerimg, String nick, int utype, String imuseraccount, String signdesc, int openlocation, int openmsgalert) {
        this.id = id;
        this.usercode = usercode;
        this.username = username;
        this.pwd = pwd;
        this.sex = sex;
        this.birthday = birthday;
        this.headerimg = headerimg;
        this.nick = nick;
        this.utype = utype;
        this.imuseraccount = imuseraccount;
        this.signdesc = signdesc;
        this.openlocation = openlocation;
        this.openmsgalert = openmsgalert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHeaderimg() {
        return headerimg;
    }

    public void setHeaderimg(String headerimg) {
        this.headerimg = headerimg;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getUtype() {
        return utype;
    }

    public void setUtype(int utype) {
        this.utype = utype;
    }

    public String getImuseraccount() {
        return imuseraccount;
    }

    public void setImuseraccount(String imuseraccount) {
        this.imuseraccount = imuseraccount;
    }

    public String getSigndesc() {
        return signdesc;
    }

    public void setSigndesc(String signdesc) {
        this.signdesc = signdesc;
    }

    public int getOpenlocation() {
        return openlocation;
    }

    public void setOpenlocation(int openlocation) {
        this.openlocation = openlocation;
    }

    public int getOpenmsgalert() {
        return openmsgalert;
    }

    public void setOpenmsgalert(int openmsgalert) {
        this.openmsgalert = openmsgalert;
    }
}
