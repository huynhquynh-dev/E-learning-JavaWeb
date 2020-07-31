package com.myclass.model.mapper.impl;

import com.myclass.model.entity.*;
import com.myclass.model.mapper.CourseMapper;
import com.myclass.model.mapper.VideoMapper;
import com.myclass.model.request.AccountRequest;
import com.myclass.model.request.CourseRequest;
import com.myclass.model.request.VideoRequest;
import com.myclass.model.response.CourseResponse;
import com.myclass.model.response.TargetResponse;
import com.myclass.model.response.VideoResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseResponse toDTO(Course course) {
        if (course == null) {
            return null;
        }
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setTitle(course.getTitle());
        courseResponse.setImage(course.getImage());
        courseResponse.setLeturesCount(course.getLeturesCount());
        courseResponse.setHourCount(course.getHourCount());
        courseResponse.setViewCount(course.getViewCount());
        courseResponse.setPrice(course.getPrice());
        courseResponse.setDiscount(course.getDiscount());
        courseResponse.setPromotionPrice(course.getPromotionPrice());
        courseResponse.setDescription(course.getDescription());
        courseResponse.setContent(course.getContent());
        courseResponse.setEnabled(course.isEnabled());
        courseResponse.setCategoryId(course.getCategory().getId());
        return courseResponse;
    }

    @Override
    public List<CourseResponse> toListDTO(List<Course> courses) {
        if (courses == null) {
            return null;
        }
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (Course course : courses) {
            courseResponses.add(toDTO(course));
        }
        return courseResponses;
    }

    @Override
    public Course toEntity(Course course, CourseRequest courseRequest) {
        if (courseRequest == null) {
            return null;
        }
        if (courseRequest.getId() > 0) {
            course.setId(course.getId());
        }
        course.setTitle(courseRequest.getTitle());
        course.setImage(courseRequest.getImage());
        course.setLeturesCount(courseRequest.getLeturesCount());
        course.setHourCount(courseRequest.getHourCount());
        course.setViewCount(courseRequest.getViewCount());
        course.setPrice(courseRequest.getPrice());
        course.setDiscount(courseRequest.getDiscount());
        course.setPromotionPrice(courseRequest.getPromotionPrice());
        course.setDescription(courseRequest.getDescription());
        course.setContent(courseRequest.getContent());
        course.setEnabled(courseRequest.isEnabled());
        return course;
    }

    @Override
    public List<AccountCourse> toListAccountCourseEntity(Course course, List<AccountRequest> accounts) {
        if (accounts == null) {
            return null;
        }
        List<AccountCourse> listItem = new ArrayList<>();
        for(AccountRequest accountRequest : accounts){
            AccountCourse accountCourse = new AccountCourse();
            AccountCourseId accountCourseId = new AccountCourseId();
            accountCourseId.setAccountId(accountRequest.getId());
            accountCourseId.setCourseId(course.getId());
            accountCourse.setId(accountCourseId);
            // Course
            accountCourse.setCourse(course);
            // Account
            Account account = new Account();
            account.setId(accountRequest.getId());
            accountCourse.setAccount(account);
            accountCourse.setEnabled(accountRequest.isEnabled());
            listItem.add(accountCourse);
        }
        return listItem;
    }
}
