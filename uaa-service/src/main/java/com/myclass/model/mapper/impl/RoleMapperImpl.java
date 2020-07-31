package com.myclass.model.mapper.impl;

import com.myclass.model.entity.Permission;
import com.myclass.model.entity.Role;
import com.myclass.model.entity.RolePermission;
import com.myclass.model.entity.RolePermissionId;
import com.myclass.model.mapper.RoleMapper;
import com.myclass.model.request.PermissionRequest;
import com.myclass.model.request.RoleRequest;
import com.myclass.model.response.RoleResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleResponse toRoleDTO(Role role) {
        if (role == null) {
            return null;
        }
        RoleResponse roleReponse = new RoleResponse();
        roleReponse.setId(role.getId());
        roleReponse.setName(role.getName());
        roleReponse.setDescription(role.getDescription());
        roleReponse.setEnabled(role.isEnabled());
        return roleReponse;
    }

    @Override
    public List<RoleResponse> toListRoleDTO(List<Role> roles) {
        if (roles == null) {
            return null;
        }
        List<RoleResponse> roleReponses = new ArrayList<>();
        for (Role role : roles) {
            roleReponses.add(toRoleDTO(role));
        }
        return roleReponses;
    }

    @Override
    public Role toRoleEntity(Role role, RoleRequest roleRequest) {
        if (roleRequest.getId() > 0) {
            role.setId(roleRequest.getId());
        }
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription());
        role.setEnabled(roleRequest.isEnabled());
        return role;
    }

    @Override
    public List<RolePermission> toListRolePermissionEntity(Role role, List<PermissionRequest> permissions) {
        if (permissions == null) {
            return null;
        }
        List<RolePermission> listItem = new ArrayList<>();
        for(PermissionRequest permissionRequest : permissions){
            RolePermission rolePermission = new RolePermission();
            RolePermissionId rolePermissionId = new RolePermissionId();
            rolePermissionId.setRoleId(role.getId());
            rolePermissionId.setPermissionId(permissionRequest.getId());
            rolePermission.setId(rolePermissionId);
            // Role
            rolePermission.setRole(role);
            // Permission
            Permission permission = new Permission();
            permission.setId(permissionRequest.getId());
            rolePermission.setPermission(permission);
            rolePermission.setEnabled(permissionRequest.isEnabled());
            listItem.add(rolePermission);
        }
        return listItem;
    }
}
