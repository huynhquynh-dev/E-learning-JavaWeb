package com.myclass.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionRequest {

    private long id;
    private String name;
    private String description;
    private boolean enabled;
}
