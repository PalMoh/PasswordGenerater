package com.techlearn.Model;

public class Password {

    private String title;
    private String password;
    private int id;

    public Password(String title, String password) {
        this.title = title;
        this.password = password;
    }

    public Password(int id , String title, String password) {
        this.id = id;
        this.title = title;
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
}
