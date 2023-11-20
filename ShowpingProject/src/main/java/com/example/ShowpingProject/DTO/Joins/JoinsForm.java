package com.example.ShowpingProject.DTO.Joins;

import com.example.ShowpingProject.entity.Users;
import lombok.Data;

@Data

public class JoinsForm {
    private String userid;
    private String userpassword;
    private String username;
    private String userbirthday;
    private String userphone;
    private String useremail;
    private String useraddr;
    private String userdetailaddr;

    public JoinsForm(String userid, String userpassword, String username, String userbirthday,
                     String userphone, String useremail, String useraddr, String userdetailaddr) {
        this.userid = userid;
        this.userpassword = userpassword;
        this.username = username;
        this.userbirthday = userbirthday;
        this.userphone = userphone;
        this.useremail = useremail;
        this.useraddr = useraddr;
        this.userdetailaddr = userdetailaddr;
    }

    @Override
    public String toString() {
        return "JoinsForm{" +
                "userid='" + userid + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", username='" + username + '\'' +
                ", userbirthday='" + userbirthday + '\'' +
                ", userphone='" + userphone + '\'' +
                ", useremail='" + useremail + '\'' +
                ", useraddr='" + useraddr + '\'' +
                ", userdetailaddr='" + userdetailaddr + '\'' +
                '}';
    }



    public Users toEntity() {
        return new Users(null, userid, userpassword, username, userbirthday, userphone, useremail,
                useraddr, userdetailaddr,null,null,null,null);
    }

}

