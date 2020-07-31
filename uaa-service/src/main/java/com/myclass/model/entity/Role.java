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
@Table(name = "role")
public class Role extends BaseEntity implements Serializable {

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @Column
    private boolean enabled;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    private List<RolePermission> permissions = new ArrayList<>();
}
