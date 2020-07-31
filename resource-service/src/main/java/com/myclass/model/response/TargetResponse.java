package com.myclass.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TargetResponse {

    private long id;
    private String title;
    private int orderIndex;
    private boolean enabled;
    private long courseId;
}
