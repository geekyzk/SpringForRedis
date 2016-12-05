package com.em248.example.entity;

/**
 * Created by tian on 2016/12/5.
 */
public class User {


    private Long id;

    private String username;

    private String nickName;

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(Long id, String username, String nickName, String password) {
        this.id = id;
        this.username = username;
        this.nickName = nickName;
        this.password = password;
    }

    public User() {

    }
}
