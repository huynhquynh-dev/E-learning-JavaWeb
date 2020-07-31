package com.myclass.model.mapper;

import com.myclass.model.entity.Role;
import com.myclass.model.entity.RolePermission;
import com.myclass.model.request.PermissionRequest;
import com.myclass.model.request.RoleRequest;
import com.myclass.model.response.RoleResponse;

import java.util.List;

public interface RoleMapper {

    RoleResponse toRoleDTO(Role role);

    List<RoleResponse> toListRoleDTO(List<Role> roles);

    Role toRoleEntity(Role role, RoleRequest roleRequest);

    List<RolePermission> toListRolePermissionEntity(Role role, List<PermissionRequest> permissions);
}
