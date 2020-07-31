package com.myclass.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class AccountCourseId implements Serializable {

    private long accountId;
    private long courseId;
}
