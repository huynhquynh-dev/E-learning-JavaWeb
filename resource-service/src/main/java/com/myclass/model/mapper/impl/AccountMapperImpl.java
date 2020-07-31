package com.myclass.model.mapper.impl;

import com.myclass.model.entity.Account;
import com.myclass.model.entity.AccountCourse;
import com.myclass.model.entity.AccountCourseId;
import com.myclass.model.entity.Course;
import com.myclass.model.mapper.AccountMapper;
import com.myclass.model.request.CourseRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public List<AccountCourse> toListAccountCourseEntity(Account account, List<CourseRequest> courses) {
        if (courses == null) {
            return null;
        }
        List<AccountCourse> listItem = new ArrayList<>();
        for(CourseRequest courseRequest : courses){
            AccountCourse accountCourse = new AccountCourse();
            AccountCourseId accountCourseId = new AccountCourseId();
            accountCourseId.setAccountId(account.getId());
            accountCourseId.setCourseId(courseRequest.getId());
            accountCourse.setId(accountCourseId);
            // Account
            accountCourse.setAccount(account);
            // Course
            Course course = new Course();
            course.setId(courseRequest.getId());
            accountCourse.setCourse(course);
            accountCourse.setEnabled(courseRequest.isEnabled());
            listItem.add(accountCourse);
        }
        return listItem;
    }
}
