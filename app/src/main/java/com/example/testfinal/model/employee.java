package com.example.testfinal.model;

public class employee {
    private String id;
    private String pass;

    public employee(String id, String pass) {
        this.id = id;
        this.pass = pass;
    }
    public employee(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
