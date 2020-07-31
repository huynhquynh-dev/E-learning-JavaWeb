package com.myclass.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionResponse {

    private long id;
    private String name;
    private String description;
    private boolean enabled;

    public PermissionResponse() {
    }

    public PermissionResponse(Long id, String name, String description, boolean enabled) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
    }
}
