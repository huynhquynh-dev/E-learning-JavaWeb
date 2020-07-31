package com.myclass.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseRequest {

    private long id;
    private String title;
    private String image;
    private int leturesCount;
    private int hourCount;
    private int viewCount;
    private double price;
    private int discount;
    private double promotionPrice;
    private String description;
    private String content;
    private boolean enabled;
    private long categoryId;
    private List<VideoRequest> videos;
    private List<TargetRequest> targets;
}
