package com.myclass.model.mapper;

import com.myclass.model.entity.Account;
import com.myclass.model.entity.AccountCourse;
import com.myclass.model.request.CourseRequest;

import java.util.List;

public interface AccountMapper {

    List<AccountCourse> toListAccountCourseEntity(Account account, List<CourseRequest> courses);
}
