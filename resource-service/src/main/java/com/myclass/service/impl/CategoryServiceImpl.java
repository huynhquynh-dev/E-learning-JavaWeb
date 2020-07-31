package com.myclass.service.impl;

import com.myclass.model.entity.Category;
import com.myclass.model.entity.Course;
import com.myclass.model.mapper.CategoryMapper;
import com.myclass.model.mapper.CourseMapper;
import com.myclass.model.request.CategoryRequest;
import com.myclass.model.request.CourseRequest;
import com.myclass.repository.CategoryRepository;
import com.myclass.repository.CourseRepository;
import com.myclass.service.CategoryService;
import com.myclass.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(long categoryId) {
        Optional<Category> optional = categoryRepository.findById(categoryId);
        return optional.orElse(null);
    }

    @Override
    public Category saveOrUpdate(CategoryRequest categoryRequest) {
        Category category = null;
        if (categoryRequest.getId() == 0) {
            category = new Category();
        }
        else {
            Optional<Category> optional = categoryRepository.findById(categoryRequest.getId());
            if (optional.isPresent()) {
                category = optional.get();
            }
        }
        category = categoryMapper.toEntity(category, categoryRequest);
        category = categoryRepository.save(category);
        return category;
    }

    @Override
    public boolean delete(long targetId) {
        Optional<Category> optional = categoryRepository.findById(targetId);
        if (optional.isPresent()) {
            Category category = optional.get();
            categoryRepository.delete(category);
            return true;
        }
        return false;
    }
}
