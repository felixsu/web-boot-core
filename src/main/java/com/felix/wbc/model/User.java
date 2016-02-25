package com.felix.wbc.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by fsoewito on 2/23/2016.
 */

@Entity
@Table(name = "data_user")
public class User {

    private static final String COL_USERNAME = "username";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";

    private static final int LEN_USERNAME = 50;
    private static final int LEN_EMAIL = 50;
    private static final int LEN_PASSWORD = 500;

    @Id
    @Column(name = COL_USERNAME, nullable = false, length = LEN_USERNAME)
    private String username;

    @Email
    @Column(name = COL_EMAIL, nullable = false, length = LEN_EMAIL)
    private String email;

    @Column(name = COL_PASSWORD, nullable = false, length = LEN_PASSWORD)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
