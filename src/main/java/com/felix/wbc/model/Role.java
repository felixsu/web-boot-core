package com.felix.wbc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.felix.wbc.constant.TableConstant;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fsoewito on 2/26/2016.
 */

@Entity
@Table(name = "data_role")
public class Role implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "data_role_id_seq", sequenceName = "data_role_id_seq")
    @GeneratedValue(generator = "data_role_id_seq")
    @Column(name = TableConstant.COL_ID, nullable = false)
    private Integer id;

    @NotEmpty
    @Column(name = TableConstant.COL_NAME, nullable = false, length = TableConstant.LEN_NAME)
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<User> users = new HashSet<User>();

    @Override
    public String getAuthority() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
