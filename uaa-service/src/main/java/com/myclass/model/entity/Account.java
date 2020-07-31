package com.myclass.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.*;

//        Hàm kiểm tra User: bắt buộc User có các thuộc tính enabled, accountNonLocked, accountNonExpired, credentialsNonExpired;
//        KHÓA                  -> user.isAccountNonLocked() : User account is locked
//        KHÔNG HOẠT ĐỘNG       -> user.isEnabled() : User is disabled
//        HẾT HẠN               -> user.isAccountNonExpired() : User account has expired
//        PHIÊN LOGIN HẾT HẠN   ->user.isCredentialsNonExpired() : User credentials have expired

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account extends BaseEntity implements Serializable, UserDetails {

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

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AccountRole> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getRole().getName()));
            r.getRole().getPermissions().forEach(p -> {
                authorities.add(new SimpleGrantedAuthority(p.getPermission().getName()));
            });
        });
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountNonExpired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountNonLocked;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
