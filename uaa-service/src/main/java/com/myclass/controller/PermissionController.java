package com.myclass.controller;

import com.myclass.model.entity.Permission;
import com.myclass.model.mapper.PermissionMapper;
import com.myclass.model.request.PermissionRequest;
import com.myclass.model.response.PermissionResponse;
import com.myclass.model.response.RestResult;
import com.myclass.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @Autowired
    PermissionMapper permissionMapper;

    @GetMapping
    public Object findAll() {
        RestResult<List<PermissionResponse>> result = new RestResult<>();
        result.ok(permissionMapper.toListPermissionDTO(permissionService.findAll()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Object findById(@PathVariable("id") long id) {
        RestResult<PermissionResponse> result = new RestResult<>();
        Permission permission = permissionService.findById(id);
        if (permission != null) {
            PermissionResponse permissionResponse = permissionMapper.toPermissionDTO(permission);
            result.ok(permissionResponse);
        }
        else {
            result.fail();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public Object save(@RequestBody PermissionRequest permissionRequest) {
        RestResult<PermissionResponse> result = new RestResult<>();
        result.ok(permissionMapper.toPermissionDTO(permissionService.saveOrUpdate(permissionRequest)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    public Object update(@RequestBody PermissionRequest permissionRequest) {
        RestResult<PermissionResponse> result = new RestResult<>();
        result.ok(permissionMapper.toPermissionDTO(permissionService.saveOrUpdate(permissionRequest)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable(value = "id") long id) {
        RestResult<String> result = new RestResult<>();
        boolean temp = permissionService.delete(id);
        if (temp) {
            result.ok("Xóa thành công");
        }
        else {
            result.fail();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
