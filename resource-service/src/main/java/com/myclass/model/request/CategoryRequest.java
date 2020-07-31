package com.myclass.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryRequest {

    private long id;
    private String title;
    private String icon;
    private boolean enabled;
}
