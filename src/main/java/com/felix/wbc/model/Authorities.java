package com.felix.wbc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.felix.wbc.constant.TableConstant;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by fsoewito on 2/26/2016.
 */

@Entity
@Table(name = TableConstant.TABLE_AUTHORITIES)
public class Authorities implements GrantedAuthority {

    public static final String GUEST = "GUEST";
    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = TableConstant.SEQ_AUTHORITIES, sequenceName = TableConstant.SEQ_AUTHORITIES)
    @GeneratedValue(generator = TableConstant.SEQ_AUTHORITIES)
    @Column(name = TableConstant.COL_ID)
    private Integer id;

    @NotEmpty
    @Column(name = TableConstant.COL_AUTHORITY, nullable = false, length = TableConstant.LEN_AUTHORITY)
    private String authority;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<Users> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }
}
