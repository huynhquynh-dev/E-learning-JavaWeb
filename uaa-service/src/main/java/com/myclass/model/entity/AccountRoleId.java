package com.myclass.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class AccountRoleId implements Serializable {

    private long accountId;
    private long roleId;
}
