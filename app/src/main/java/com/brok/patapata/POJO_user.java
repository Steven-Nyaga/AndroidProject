package com.brok.patapata;

public class POJO_user {
    public String status;
    public String email;

    public POJO_user(){

    }
    public POJO_user(String status, String email) {
        this.email = email;
        this.status = status;
    }
}
