package com.myclass.service.impl;

import com.myclass.model.entity.Role;
import com.myclass.model.entity.RolePermission;
import com.myclass.model.mapper.RoleMapper;
import com.myclass.model.request.RoleRequest;
import com.myclass.repository.RolePermissionRepository;
import com.myclass.repository.RoleRepository;
import com.myclass.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RolePermissionRepository rolePermissionRepository;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(long roleId) {
        Optional<Role> optional = roleRepository.findById(roleId);
        return optional.orElse(null);
    }

    @Override
    public Role saveOrUpdate(RoleRequest roleRequest) {
        Role role = null;
        if (roleRequest.getId() == 0) {
            role = new Role();
        }
        else {
            Optional<Role> optional = roleRepository.findById(roleRequest.getId());
            if (optional.isPresent()) {
                role = optional.get();
            }
        }
        role = roleMapper.toRoleEntity(role, roleRequest);
        role = roleRepository.save(role);
        List<RolePermission> rolePermissions = roleMapper.toListRolePermissionEntity(role, roleRequest.getPermissions());
        rolePermissions = rolePermissionRepository.saveAll(rolePermissions);
        role.setPermissions(rolePermissions);
        return role;
    }

    @Override
    public boolean delete(long roleId) {
        Optional<Role> optional = roleRepository.findById(roleId);
        if (optional.isPresent()) {
            Role role = optional.get();
            roleRepository.delete(role);
            return true;
        }
        return false;
    }
}
