package com.myclass.model.mapper.impl;

import com.myclass.model.entity.Category;
import com.myclass.model.entity.Course;
import com.myclass.model.entity.Target;
import com.myclass.model.mapper.CategoryMapper;
import com.myclass.model.request.CategoryRequest;
import com.myclass.model.response.CategoryResponse;
import com.myclass.model.response.TargetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryResponse toDTO(Category category) {
        if (category == null) {
            return null;
        }
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setTitle(category.getTitle());
        categoryResponse.setIcon(category.getIcon());
        categoryResponse.setEnabled(category.isEnabled());
        return categoryResponse;
    }

    @Override
    public List<CategoryResponse> toListDTO(List<Category> categories) {
        if (categories == null) {
            return null;
        }
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : categories) {
            categoryResponses.add(toDTO(category));
        }
        return categoryResponses;
    }

    @Override
    public Category toEntity(Category category, CategoryRequest categoryRequest) {
        if (categoryRequest == null) {
            return null;
        }
        if (categoryRequest.getId() > 0) {
            category.setId(categoryRequest.getId());
        }
        category.setId(categoryRequest.getId());
        category.setTitle(categoryRequest.getTitle());
        category.setIcon(categoryRequest.getIcon());
        category.setEnabled(categoryRequest.isEnabled());
        return category;
    }
}
