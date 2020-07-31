package com.myclass.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountResponse {

    // Account
    private long id;
    private String email;
    private String fullName;
    private String avatar;
    private String phone;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean enabled;

    // Role
    private long roleId;
    private List<String> roles;
}
