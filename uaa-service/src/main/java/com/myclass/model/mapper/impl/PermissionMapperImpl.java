package com.myclass.model.mapper.impl;

import com.myclass.model.entity.Permission;
import com.myclass.model.entity.RolePermission;
import com.myclass.model.mapper.PermissionMapper;
import com.myclass.model.request.PermissionRequest;
import com.myclass.model.response.PermissionResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PermissionMapperImpl implements PermissionMapper {

    @Override
    public PermissionResponse toPermissionDTO(Permission permission) {
        if (permission == null) {
            return null;
        }
        PermissionResponse permissionReponse = new PermissionResponse();
        permissionReponse.setId(permission.getId());
        permissionReponse.setName(permission.getName());
        permissionReponse.setDescription(permission.getDescription());
        permissionReponse.setEnabled(permission.isEnabled());
        return permissionReponse;
    }

    @Override
    public List<PermissionResponse> toListPermissionDTO(List<Permission> permissions) {
        if (permissions == null) {
            return null;
        }
        List<PermissionResponse> permissionReponses = new ArrayList<>();
        for (Permission permission : permissions) {
            permissionReponses.add(toPermissionDTO(permission));
        }
        return permissionReponses;
    }

    @Override
    public List<PermissionResponse> toListRolePermissionDTO(List<RolePermission> rolePermissions) {
        if (rolePermissions == null) {
            return null;
        }
        List<PermissionResponse> permissionReponses = new ArrayList<>();
        for (RolePermission rolePermission : rolePermissions) {
            permissionReponses.add(toPermissionDTO(rolePermission.getPermission()));
        }
        return permissionReponses;
    }

    @Override
    public Permission toPermissionEntity(Permission permission, PermissionRequest permissionRequest) {
        if (permissionRequest.getId() > 0) {
            permission.setId(permissionRequest.getId());
        }
        permission.setName(permissionRequest.getName());
        permission.setDescription(permissionRequest.getDescription());
        permission.setEnabled(permissionRequest.isEnabled());
        return permission;
    }
}
