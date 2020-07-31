package com.myclass.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseResponse {

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
    private List<VideoResponse> videos;
    private List<TargetResponse> targets;

    public CourseResponse() {
    }

    public CourseResponse(long id, String title, String image,
                          int leturesCount, int hourCount,
                          int viewCount, double price, int discount,
                          double promotionPrice, String description,
                          String content, boolean enabled) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.leturesCount = leturesCount;
        this.hourCount = hourCount;
        this.viewCount = viewCount;
        this.price = price;
        this.discount = discount;
        this.promotionPrice = promotionPrice;
        this.description = description;
        this.content = content;
        this.enabled = enabled;
    }
}
