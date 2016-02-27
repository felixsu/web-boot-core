package com.felix.wbc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.felix.wbc.constant.TableConstant;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fsoewito on 2/23/2016.
 */

@Entity
@Table(name = "data_user")
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "data_user_id_seq", sequenceName = "data_user_id_seq")
    @GeneratedValue(generator = "data_user_id_seq")
    @Column(name = TableConstant.COL_ID, nullable = false)
    private Integer id;

    @Column(name = TableConstant.COL_USERNAME, nullable = false, length = TableConstant.LEN_USERNAME)
    private String username;

    @Email
    @Column(name = TableConstant.COL_EMAIL, nullable = false, length = TableConstant.LEN_EMAIL)
    private String email;

    @Column(name = TableConstant.COL_PASSWORD, nullable = false, length = TableConstant.LEN_PASSWORD)
    private String password;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<Role>();

    public User() {
    }

    public User(User user) {
        super();
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
