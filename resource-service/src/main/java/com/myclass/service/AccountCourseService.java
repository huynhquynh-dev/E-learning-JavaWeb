package com.myclass.service;

import com.myclass.model.response.AccountResponse;
import com.myclass.model.response.CourseResponse;

import java.util.List;

public interface AccountCourseService {

    List<CourseResponse> findByAccount(long accountId);

    List<AccountResponse> findByCourse(long courseId);
}
