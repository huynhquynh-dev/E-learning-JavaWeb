package com.myclass.model.request;

import com.myclass.model.response.CourseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountRequest {

    //Account
    private long id;
    private String email;
    private String password;
    private String fullName;
    private String avatar;
    private String phone;
    private boolean enabled;

    //Course
    private List<CourseResponse> courses;
}
