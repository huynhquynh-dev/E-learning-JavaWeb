package com.myclass.controller;

import com.myclass.model.entity.Course;
import com.myclass.model.entity.Video;
import com.myclass.model.mapper.CourseMapper;
import com.myclass.model.mapper.TargetMapper;
import com.myclass.model.mapper.VideoMapper;
import com.myclass.model.request.CourseRequest;
import com.myclass.model.request.VideoRequest;
import com.myclass.model.response.AccountResponse;
import com.myclass.model.response.CourseResponse;
import com.myclass.model.response.RestResult;
import com.myclass.model.response.VideoResponse;
import com.myclass.service.AccountCourseService;
import com.myclass.service.CourseService;
import com.myclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    VideoMapper videoMapper;

    @Autowired
    TargetMapper targetMapper;

    @Autowired
    AccountCourseService accountCourseService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
    public Object findAll() {
        RestResult<List<CourseResponse>> result = new RestResult<>();
        result.ok(courseMapper.toListDTO(courseService.findAll()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
    public Object findById(@PathVariable("id") long id) {
        RestResult<CourseResponse> result = new RestResult<>();
        Course course = courseService.findById(id);
        if (course != null) {
            CourseResponse courseResponse = courseMapper.toDTO(course);
            courseResponse.setVideos(videoMapper.toListDTO(course.getVideos()));
            courseResponse.setTargets(targetMapper.toListDTO(course.getTargets()));
            result.ok(courseResponse);
        }
        else {
            result.fail();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('role_admin')")
    public Object save(@RequestBody CourseRequest courseRequest) {
        RestResult<CourseResponse> result = new RestResult<>();
        Course course = courseService.saveOrUpdate(courseRequest);
        if (course == null) {
            result.fail();
        }
        else {
            result.ok(courseMapper.toDTO(course));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('role_admin')")
    public Object update(@RequestBody CourseRequest courseRequest) {
        RestResult<CourseResponse> result = new RestResult<>();
        Course course = courseService.saveOrUpdate(courseRequest);
        if (course == null) {
            result.fail();
        }
        else {
            result.ok(courseMapper.toDTO(course));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('role_admin')")
    public Object delete(@PathVariable(value = "id") long id) {
        RestResult<String> result = new RestResult<>();
        if (courseService.delete(id)) {
            result.ok("Xóa thành công");
        }
        else {
            result.fail();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}/account")
    @PreAuthorize("hasAuthority('role_admin')")
    public Object findByAccounts(@PathVariable(value = "id") long id) {
        RestResult<List<AccountResponse>> result = new RestResult<>();
        result.ok(accountCourseService.findByCourse(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
