package com.techlearn.Model;

import java.io.Serializable;

public class Password implements Serializable {

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
