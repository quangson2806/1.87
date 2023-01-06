package com.edu.usth.finalappmobile;

public class User {
    private String email;
    private String password;
    private String username;
    private String phone;

    public User() {
    }

    public User(String email, String password, String username, String phone) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }
}
