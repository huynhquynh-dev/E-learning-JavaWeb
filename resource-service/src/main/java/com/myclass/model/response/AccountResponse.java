package com.myclass.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountResponse {

    //Account
    private long id;
    private String email;
    private String fullName;
    private String avatar;
    private String phone;
    private boolean enabled;

    //Course
    private List<CourseResponse> courses;

    public AccountResponse() {
    }

    public AccountResponse(long id, String email,
                           String fullName, String avatar,
                           String phone, boolean enabled) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.avatar = avatar;
        this.phone = phone;
        this.enabled = enabled;
    }
}
