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
@Table(name = TableConstant.TABLE_USERS)
public class Users {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = TableConstant.SEQ_USERS, sequenceName = TableConstant.SEQ_USERS)
    @GeneratedValue(generator = TableConstant.SEQ_USERS)
    @Column(name = TableConstant.COL_ID)
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
    @JoinTable(
            name = TableConstant.TABLE_USERS_AUTHORITIES,
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Authorities> roles = new HashSet<Authorities>();

    public Users() {
    }

    public Users(Users users) {
        this.id = users.getId();
        this.username = users.getUsername();
        this.email = users.getEmail();
        this.password = users.getPassword();
        this.roles = users.getRoles();
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

    public Set<Authorities> getRoles() {
        return roles;
    }

    public void setRoles(Set<Authorities> roles) {
        this.roles = roles;
    }
}
