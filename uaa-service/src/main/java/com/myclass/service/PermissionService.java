package com.myclass.service;

import com.myclass.model.entity.Permission;
import com.myclass.model.request.PermissionRequest;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll();

    Permission findById(long permissionId);

    Permission saveOrUpdate(PermissionRequest permissionRequest);

    boolean delete(long permissionId);
}
