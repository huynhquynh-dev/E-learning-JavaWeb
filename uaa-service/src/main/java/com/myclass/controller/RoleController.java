package com.myclass.controller;

import com.myclass.model.entity.Role;
import com.myclass.model.entity.RolePermission;
import com.myclass.model.mapper.PermissionMapper;
import com.myclass.model.mapper.RoleMapper;
import com.myclass.model.request.RoleRequest;
import com.myclass.model.response.PermissionResponse;
import com.myclass.model.response.RestResult;
import com.myclass.model.response.RoleResponse;
import com.myclass.service.RolePermissionService;
import com.myclass.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    RolePermissionService rolePermissionService;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @GetMapping
    public Object findAll() {
        RestResult<List<RoleResponse>> result = new RestResult<>();
        result.ok(roleMapper.toListRoleDTO(roleService.findAll()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Object findById(@PathVariable("id") long id) {
        RestResult<RoleResponse> result = new RestResult<>();
        Role role = roleService.findById(id);
        if (role != null) {
            RoleResponse roleReponse = roleMapper.toRoleDTO(role);
            roleReponse.setPermissions(permissionMapper.toListRolePermissionDTO(role.getPermissions()));
            result.ok(roleReponse);
        }
        else {
            result.fail();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public Object save(@RequestBody RoleRequest roleRequest) {
        RestResult<RoleResponse> result = new RestResult<>();
        result.ok(roleMapper.toRoleDTO(roleService.saveOrUpdate(roleRequest)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    public Object update(@RequestBody RoleRequest roleRequest) {
        RestResult<RoleResponse> result = new RestResult<>();
        result.ok(roleMapper.toRoleDTO(roleService.saveOrUpdate(roleRequest)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable(value = "id") long id) {
        RestResult<String> result = new RestResult<>();
        boolean temp = roleService.delete(id);
        if (temp) {
            result.ok("Xóa thành công!");
        } else {
            result.fail();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}/permission")
    public Object findByPermissions(@PathVariable(value = "id") long id) {
        RestResult<List<PermissionResponse>> result = new RestResult<>();
        result.ok(rolePermissionService.findByRole(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
