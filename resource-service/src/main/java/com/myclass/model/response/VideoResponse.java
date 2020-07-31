package com.myclass.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoResponse {

    private long id;
    private String title;
    private int timeCount;
    private String url;
    private boolean enabled;
    private long courseId;
}
