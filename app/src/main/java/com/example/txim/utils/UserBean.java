package com.example.txim.utils;

import java.io.Serializable;

public class UserBean implements Serializable {
    private int id;
    private String name;
    private String password;
    private String autograph;
    private String username;

    public UserBean() {
    }

    public UserBean(String name, String password,String autograph,String username) {
        this.name = name;
        this.password = password;
        this.autograph = autograph;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password="
                + password + ",autograph"+autograph+"]";
    }
}
