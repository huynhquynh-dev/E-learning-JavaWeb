package com.myclass.model.mapper;

import com.myclass.model.entity.Permission;
import com.myclass.model.entity.RolePermission;
import com.myclass.model.request.PermissionRequest;
import com.myclass.model.response.PermissionResponse;

import java.util.List;

public interface PermissionMapper {

    PermissionResponse toPermissionDTO(Permission permission);

    List<PermissionResponse> toListPermissionDTO(List<Permission> permissions);

    List<PermissionResponse> toListRolePermissionDTO(List<RolePermission> rolePermissions);

    Permission toPermissionEntity(Permission permission, PermissionRequest permissionRequest);
}
