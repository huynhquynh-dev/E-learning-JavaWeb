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
@Table(name = "video")
public class Video extends Base implements Serializable {

    @Column(nullable = false, unique = true)
    private String title;

    @Column(name = "time_count")
    private int timeCount;

    @Column
    private String url;

    @Column
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
