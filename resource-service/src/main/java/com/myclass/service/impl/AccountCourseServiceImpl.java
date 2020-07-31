package com.myclass.service.impl;

import com.myclass.model.entity.Account;
import com.myclass.model.entity.Course;
import com.myclass.model.response.AccountResponse;
import com.myclass.model.response.CourseResponse;
import com.myclass.repository.AccountCourseRepository;
import com.myclass.service.AccountCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountCourseServiceImpl implements AccountCourseService {

    @Autowired
    AccountCourseRepository accountCourseRepository;

    @Override
    public List<CourseResponse> findByAccount(long accountId) {
        Account account = new Account();
        account.setId(accountId);
        return accountCourseRepository.findByAccount(account);
    }

    @Override
    public List<AccountResponse> findByCourse(long courseId) {
        Course course = new Course();
        course.setId(courseId);
        return accountCourseRepository.findByCourse(course);
    }
}
