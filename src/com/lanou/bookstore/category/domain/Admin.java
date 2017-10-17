package com.lanou.bookstore.category.domain;

import java.io.Serializable;

/**
 * Created by dllo on 17/9/22.
 */
public class Admin implements Serializable {
    private String aid;
    private String aname;
    private String password;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aid='" + aid + '\'' +
                ", aname='" + aname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Admin(String aid, String aname, String password) {
        this.aid = aid;
        this.aname = aname;
        this.password = password;
    }

    public Admin() {

    }
}
