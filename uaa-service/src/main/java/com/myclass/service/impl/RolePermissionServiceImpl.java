package com.myclass.service.impl;

import com.myclass.model.entity.Role;
import com.myclass.model.response.PermissionResponse;
import com.myclass.repository.RolePermissionRepository;
import com.myclass.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    RolePermissionRepository rolePermissionRepository;

    @Override
    public List<PermissionResponse> findByRole(long roleId) {
        Role role = new Role();
        role.setId(roleId);
        List<PermissionResponse> permissionResponses = rolePermissionRepository.findByRole(role);
        return permissionResponses;
    }
}
