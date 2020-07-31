package com.myclass.service;

import com.myclass.model.entity.Category;
import com.myclass.model.request.CategoryRequest;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(long categoryId);

    Category saveOrUpdate(CategoryRequest categoryRequest);

    boolean delete(long categoryId);
}
