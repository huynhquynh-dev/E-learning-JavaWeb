package com.myclass.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryResponse {

    private long id;
    private String title;
    private String icon;
    private boolean enabled;
    private List<CourseResponse> courses;
}
