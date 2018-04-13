package com.example.sarovar.medauth;


/**
 * Created by Sarovar on 23-03-2018.
 */

public class UserInfo {

    public String name,sid,email,phone,aphone;

    public UserInfo() {
    }

    public UserInfo(String name, String sid, String email, String phone, String aphone) {
        this.name = name;
        this.sid = sid;
        this.email = email;
        this.phone = phone;
        this.aphone = aphone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAphone() {
        return aphone;
    }

    public void setAphone(String aphone) {
        this.aphone = aphone;
    }
}
