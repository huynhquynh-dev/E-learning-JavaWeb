package com.myclass.service;

import com.myclass.model.entity.Role;
import com.myclass.model.request.RoleRequest;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(long roleId);

    Role saveOrUpdate(RoleRequest roleRequest);

    boolean delete(long roleId);
}
