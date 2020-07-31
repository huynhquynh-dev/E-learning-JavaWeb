package com.myclass.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TargetRequest {

    private long id;
    private String title;
    private int orderIndex;
    private boolean enabled;
    private long courseId;
}
