package com.myclass.service.impl;

import com.myclass.model.entity.Category;
import com.myclass.model.entity.Course;
import com.myclass.model.mapper.CourseMapper;
import com.myclass.model.request.CourseRequest;
import com.myclass.repository.CategoryRepository;
import com.myclass.repository.CourseRepository;
import com.myclass.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(long courseId) {
        Optional<Course> optional = courseRepository.findById(courseId);
        return optional.orElse(null);
    }

    @Override
    public Course saveOrUpdate(CourseRequest courseRequest) {
        Course course = null;
        if (courseRequest.getId() == 0) {
            course = new Course();
        }
        else {
            Optional<Course> optional = courseRepository.findById(courseRequest.getId());
            if (optional.isPresent()) {
                course = optional.get();
            }
        }
        course = courseMapper.toEntity(course, courseRequest);
        Optional<Category> optionalCategory = categoryRepository.findById(courseRequest.getCategoryId());
        if (optionalCategory.isPresent()) {
            course.setCategory(optionalCategory.get());
            course = courseRepository.save(course);
            return course;
        }
        return null;
    }

    @Override
    public boolean delete(long targetId) {
        Optional<Course> optional = courseRepository.findById(targetId);
        if (optional.isPresent()) {
            Course course = optional.get();
            courseRepository.delete(course);
            return true;
        }
        return false;
    }
}
