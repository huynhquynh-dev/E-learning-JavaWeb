package com.myclass.controller;

import com.myclass.model.response.CourseResponse;
import com.myclass.model.response.RestResult;
import com.myclass.service.AccountCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    AccountCourseService accountCourseService;

    @GetMapping("/{id}/course")
    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
    public Object findByCourses(@PathVariable(value = "id") long id) {
        RestResult<List<CourseResponse>> result = new RestResult<>();
        result.ok(accountCourseService.findByAccount(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
