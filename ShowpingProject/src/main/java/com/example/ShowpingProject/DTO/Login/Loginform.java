package com.example.ShowpingProject.DTO.Login;

import lombok.Getter;

public class Loginform {

    String login_ID;
    String login_password;

    public Loginform(String login_ID, String login_password) {
        this.login_ID = login_ID;
        this.login_password = login_password;
    }

    public String getLogin_ID() {
        return login_ID;
    }

    public String getLogin_password() {
        return login_password;
    }

    @Override
    public String toString() {
        return "Loginform{" +
                "login_ID='" + login_ID + '\'' +
                ", login_password='" + login_password + '\'' +
                '}';
    }
}
