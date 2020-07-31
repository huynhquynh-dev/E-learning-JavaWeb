package com.myclass.model.mapper;

import com.myclass.model.entity.AccountCourse;
import com.myclass.model.entity.Course;
import com.myclass.model.request.AccountRequest;
import com.myclass.model.request.CourseRequest;
import com.myclass.model.response.CourseResponse;

import java.util.List;

public interface CourseMapper {

    CourseResponse toDTO(Course course);

    List<CourseResponse> toListDTO(List<Course> courses);

    Course toEntity(Course course, CourseRequest courseRequest);

    List<AccountCourse> toListAccountCourseEntity(Course course, List<AccountRequest> accounts);
}
