package com.myclass.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "account_course")
public class AccountCourse implements Serializable {

    @EmbeddedId
    private AccountCourseId id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @MapsId("accountId")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @MapsId("courseId")
    private Course course;

    @Column(name = "role_id")
    private long roleId;

    @Column
    private boolean enabled;
}
