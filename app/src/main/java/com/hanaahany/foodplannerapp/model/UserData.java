package com.hanaahany.foodplannerapp.model;

public class UserData {
    private String email;
    private String userName;
    private String imageUser;

    public UserData(String email, String userName, String imageUser) {
        this.email = email;
        this.userName = userName;
        this.imageUser = imageUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUser() {
        return imageUser;
    }

    public void setImageUser(String imageUser) {
        this.imageUser = imageUser;
    }
}
