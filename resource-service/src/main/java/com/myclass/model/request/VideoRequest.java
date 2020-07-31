package com.myclass.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoRequest {

    private long id;
    private String title;
    private int timeCount;
    private String url;
    private boolean enabled;
    private long courseId;
}
