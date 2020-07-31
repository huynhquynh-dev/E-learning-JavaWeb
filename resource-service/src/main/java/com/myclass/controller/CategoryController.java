package com.myclass.controller;

import com.myclass.model.entity.Category;
import com.myclass.model.entity.Course;
import com.myclass.model.mapper.CategoryMapper;
import com.myclass.model.mapper.CourseMapper;
import com.myclass.model.request.CategoryRequest;
import com.myclass.model.request.CourseRequest;
import com.myclass.model.response.CategoryResponse;
import com.myclass.model.response.CourseResponse;
import com.myclass.model.response.RestResult;
import com.myclass.service.CategoryService;
import com.myclass.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    CourseMapper courseMapper;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
    public Object findAll() {
        RestResult<List<CategoryResponse>> result = new RestResult<>();
        result.ok(categoryMapper.toListDTO(categoryService.findAll()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
    public Object findById(@PathVariable("id") long id) {
        RestResult<CategoryResponse> result = new RestResult<>();
        Category category = categoryService.findById(id);
        if (category != null) {
            CategoryResponse categoryResponse = categoryMapper.toDTO(category);
            categoryResponse.setCourses(courseMapper.toListDTO(category.getCourses()));
            result.ok(categoryResponse);
        }
        else {
            result.fail();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('role_admin')")
    public Object save(@RequestBody CategoryRequest categoryRequest) {
        RestResult<CategoryResponse> result = new RestResult<>();
        result.ok(categoryMapper.toDTO(categoryService.saveOrUpdate(categoryRequest)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('role_admin')")
    public Object update(@RequestBody CategoryRequest categoryRequest) {
        RestResult<CategoryResponse> result = new RestResult<>();
        result.ok(categoryMapper.toDTO(categoryService.saveOrUpdate(categoryRequest)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('role_admin')")
    public Object delete(@PathVariable(value = "id") long id) {
        RestResult<String> result = new RestResult<>();
        if (categoryService.delete(id)) {
            result.ok("Xóa thành công");
        }
        else {
            result.fail();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
