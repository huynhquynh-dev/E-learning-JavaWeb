package com.myclass.service.impl;

import com.myclass.model.entity.Permission;
import com.myclass.model.mapper.PermissionMapper;
import com.myclass.model.request.PermissionRequest;
import com.myclass.repository.PermissionRepository;
import com.myclass.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission findById(long permissionId) {
        Optional<Permission> optional = permissionRepository.findById(permissionId);
        return optional.orElse(null);
    }

    @Override
    public Permission saveOrUpdate(PermissionRequest permissionRequest) {
        Permission permission = null;
        if (permissionRequest.getId() == 0) {
            permission = new Permission();
        }
        else {
            Optional<Permission> optional = permissionRepository.findById(permissionRequest.getId());
            if (optional.isPresent()){
                permission = optional.get();
            }
        }
        permission = permissionMapper.toPermissionEntity(permission, permissionRequest);
        permissionRepository.save(permission);
        return permission;
    }

    @Override
    public boolean delete(long permissionId) {
        Optional<Permission> optional = permissionRepository.findById(permissionId);
        if (optional.isPresent()) {
            Permission permission = optional.get();
            permissionRepository.delete(permission);
            return true;
        }
        return false;
    }
}
