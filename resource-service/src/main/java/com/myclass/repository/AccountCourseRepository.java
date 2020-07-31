package com.myclass.repository;

import com.myclass.model.entity.Account;
import com.myclass.model.entity.AccountCourse;
import com.myclass.model.entity.Course;
import com.myclass.model.response.AccountResponse;
import com.myclass.model.response.CourseResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AccountCourseRepository extends JpaRepository<AccountCourse, Long> {

    @Query("SELECT new com.myclass.model.response.CourseResponse(" +
            "p.id, p.title, p.image, p.leturesCount, p.hourCount, p.viewCount, " +
            "p.price, p.discount, p.promotionPrice, p.description, p.content, p.enabled) " +
            "FROM Course as p INNER JOIN AccountCourse as r " +
            "ON r.course.id = p.id AND r.account = :account")
    List<CourseResponse> findByAccount(@Param("account") Account account);

    @Query("SELECT new com.myclass.model.response.AccountResponse(" +
            "p.id, p.email, p.fullName, p.avatar, p.phone, p.enabled) " +
            "FROM Account as p INNER JOIN AccountCourse as r " +
            "ON r.account.id = p.id AND r.course = :course")
    List<AccountResponse> findByCourse(@Param("course") Course course);

//    @Query("SELECT new com.myclass.model.response.CourseResponse(" +
//            "p.id, p.title, p.image, p.leturesCount, p.hourCount, p.viewCount, " +
//            "p.price, p.discount, p.promotionPrice, p.description, p.content, " +
//            "CASE WHEN (r.id is not null) THEN r.enabled ELSE false END) " +
//            "FROM Course as p LEFT JOIN AccountCourse as r " +
//            "ON r.course.id = p.id AND r.account = :account")
//    List<CourseResponse> findByAccount(@Param("account") Account account);
//
//    @Query("SELECT new com.myclass.model.response.AccountResponse(" +
//            "p.id, p.email, p.fullName, p.avatar, p.phone, " +
//            "CASE WHEN (r.id is not null) THEN r.enabled ELSE false END) " +
//            "FROM Account as p LEFT JOIN AccountCourse as r " +
//            "ON r.account.id = p.id AND r.account = :course")
//    List<AccountResponse> findByCourse(@Param("course") Course course);
}
