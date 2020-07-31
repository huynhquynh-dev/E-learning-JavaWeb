package com.myclass.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account extends Base implements Serializable {

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private String avatar;

    @Column
    private String phone;

    @Column(name = "account_expired")
    private boolean accountNonExpired;

    @Column(name = "account_locked")
    private boolean accountNonLocked;

    @Column(name = "credentials_expired")
    private boolean credentialsNonExpired;

    @Column
    private boolean enabled;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AccountCourse> courses = new ArrayList<>();

    public Account() {
    }

    public Account(String email) {
        this.email = email;
    }
}
