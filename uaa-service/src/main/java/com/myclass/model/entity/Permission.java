package com.myclass.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "permission")
public class Permission extends BaseEntity implements Serializable {

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @Column
    private boolean enabled;
}
