package com.myclass.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class RolePermissionId implements Serializable {

    private long roleId;
    private long permissionId;
}
