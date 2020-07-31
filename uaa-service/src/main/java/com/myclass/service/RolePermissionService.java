package com.myclass.service;

import com.myclass.model.response.PermissionResponse;

import java.util.List;

public interface RolePermissionService {

    List<PermissionResponse> findByRole(long roleId);
}
