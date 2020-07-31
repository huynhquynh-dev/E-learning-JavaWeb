package com.myclass.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountRequest {

    // User
    private long id;
    private String email;
    private String fullName;
    private String avatar;
    private String phone;
    private boolean enabled;

    // Role
    private List<RoleRequest> roles;
}
