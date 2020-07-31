package com.myclass.model.response;

import com.myclass.model.entity.RolePermission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleResponse {

    // Role
    private long id;
    private String name;
    private String description;
    private boolean enabled;

    // Permission
    private List<PermissionResponse> permissions;

    public RoleResponse() {
    }

    public RoleResponse(Long id, String name, String description, boolean enabled) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
    }
}
