package com.myclass.controller;

import com.myclass.model.entity.Target;
import com.myclass.model.entity.Video;
import com.myclass.model.mapper.TargetMapper;
import com.myclass.model.mapper.VideoMapper;
import com.myclass.model.request.TargetRequest;
import com.myclass.model.request.VideoRequest;
import com.myclass.model.response.RestResult;
import com.myclass.model.response.TargetResponse;
import com.myclass.model.response.VideoResponse;
import com.myclass.service.TargetService;
import com.myclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/target")
public class TargetController {

    @Autowired
    TargetService targetService;

    @Autowired
    TargetMapper targetMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('role_admin')")
    public Object findAll() {
        RestResult<List<TargetResponse>> result = new RestResult<>();
        result.ok(targetMapper.toListDTO(targetService.findAll()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('role_admin')")
    public Object findById(@PathVariable("id") long id) {
        RestResult<TargetResponse> result = new RestResult<>();
        Target target = targetService.findById(id);
        if (target != null) {
            TargetResponse targetResponse = targetMapper.toDTO(target);
            result.ok(targetResponse);
        }
        else {
            result.fail();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('role_admin')")
    public Object save(@RequestBody TargetRequest targetRequest) {
        RestResult<TargetResponse> result = new RestResult<>();
        Target target = targetService.saveOrUpdate(targetRequest);
        if (target == null) {
            result.fail();
        }
        else {
            result.ok(targetMapper.toDTO(target));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('role_admin')")
    public Object update(@RequestBody TargetRequest targetRequest) {
        RestResult<TargetResponse> result = new RestResult<>();
        Target target = targetService.saveOrUpdate(targetRequest);
        if (target == null) {
            result.fail();
        }
        else {
            result.ok(targetMapper.toDTO(target));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('role_admin')")
    public Object delete(@PathVariable(value = "id") long id) {
        RestResult<String> result = new RestResult<>();
        if (targetService.delete(id)) {
            result.ok("Xóa thành công");
        }
        else {
            result.fail();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
