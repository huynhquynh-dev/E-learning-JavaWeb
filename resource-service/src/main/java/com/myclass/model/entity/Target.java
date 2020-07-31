package com.myclass.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "target")
public class Target extends Base implements Serializable {

    @Column(nullable = false, unique = true)
    private String title;

    @Column(name = "order_index")
    private int orderIndex;

    @Column
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
