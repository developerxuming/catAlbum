package com.test.entity;

// 实体类
public class User {
    private Integer id;
    private String studentid;
    private String name;
    private String password;
    private Integer gender;
    private String college;
    private String registertime;

    public String getRegistertime() {
        return registertime;
    }

    public void setRegistertime(String registertime) {
        this.registertime = registertime;
    }

    public String getUsercollege() {
        return college;
    }

    public void setUsercollege(String college) {
        this.college = college;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getUsername() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    public String getUserpassword() {
        return password;
    }

    public void setUserpassword(String password) {
        this.password = password;
    }

    public Integer getgender() {
        return gender;
    }

    public void setUsergender(Integer gender) {
        this.gender = gender;
    }
}
