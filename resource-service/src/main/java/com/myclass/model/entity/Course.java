package com.myclass.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "course")
public class Course extends Base implements Serializable {

    @Column(nullable = false, unique = true)
    private String title;

    @Column
    private String image;

    @Column(name = "letures_count")
    private int leturesCount; //(số bài giảng)

    @Column(name = "hour_count")
    private int hourCount; //(số giờ dạy)

    @Column(name = "view_count")
    private int viewCount; //(số lượt xem)

    @Column
    private double price; //Giá gốc

    @Column
    private int discount; //(%) giảm giá

    @Column(name = "promotion_price")
    private double promotionPrice; //(Giá khuyến mãi)

    @Column
    private String description;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private boolean enabled;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AccountCourse> accounts = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Video> videos = new ArrayList<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Target> targets = new ArrayList<>();
}
