package com.myclass.model.mapper;

import com.myclass.model.entity.Category;
import com.myclass.model.request.CategoryRequest;
import com.myclass.model.response.CategoryResponse;

import java.util.List;

public interface CategoryMapper {

    CategoryResponse toDTO(Category category);

    List<CategoryResponse> toListDTO(List<Category> categories);

    Category toEntity(Category category, CategoryRequest categoryRequest);
}
