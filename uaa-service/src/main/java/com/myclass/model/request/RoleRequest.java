package com.myclass.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleRequest {

    // Role
    private long id;
    private String name;
    private String description;
    private boolean enabled;

    // Permission
    private List<PermissionRequest> permissions;
}
