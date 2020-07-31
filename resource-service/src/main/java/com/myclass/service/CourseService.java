package com.myclass.service;

import com.myclass.model.entity.Course;
import com.myclass.model.request.CourseRequest;

import java.util.List;

public interface CourseService {

    List<Course> findAll();

    Course findById(long courseId);

    Course saveOrUpdate(CourseRequest courseRequest);

    boolean delete(long courseId);
}
