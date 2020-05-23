package com.example.quanlyquancf.DoiTuong;

public class User {
    private String Loginname;
    private  String Displayname;
    private String Password;

    public User(String loginname, String displayname, String password) {
        Loginname = loginname;
        Displayname = displayname;
        Password = password;
    }

    public String getDisplayname() {
        return Displayname;
    }

    public void setDisplayname(String displayname) {
        Displayname = displayname;
    }

    public String getLoginname() {
        return Loginname;
    }

    public void setLoginname(String loginname) {
        Loginname = loginname;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public  User(){}


}
