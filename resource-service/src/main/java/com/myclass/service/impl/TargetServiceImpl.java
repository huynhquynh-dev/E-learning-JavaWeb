package com.myclass.service.impl;

import com.myclass.model.entity.Course;
import com.myclass.model.entity.Target;
import com.myclass.model.entity.Video;
import com.myclass.model.mapper.TargetMapper;
import com.myclass.model.mapper.VideoMapper;
import com.myclass.model.request.TargetRequest;
import com.myclass.model.request.VideoRequest;
import com.myclass.repository.CourseRepository;
import com.myclass.repository.TargetRepository;
import com.myclass.repository.VideoRepository;
import com.myclass.service.TargetService;
import com.myclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TargetServiceImpl implements TargetService {

    @Autowired
    TargetRepository targetRepository;

    @Autowired
    TargetMapper targetMapper;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Target> findAll() {
        return targetRepository.findAll();
    }

    @Override
    public Target findById(long targetId) {
        Optional<Target> optional = targetRepository.findById(targetId);
        return optional.orElse(null);
    }

    @Override
    public Target saveOrUpdate(TargetRequest targetRequest) {
        Target target = null;
        if (targetRequest.getId() == 0) {
            target = new Target();
        }
        else {
            Optional<Target> optional = targetRepository.findById(targetRequest.getId());
            if (optional.isPresent()){
                target = optional.get();
            }
        }
        target = targetMapper.toEntity(target, targetRequest);
        Optional<Course> optionalCourse = courseRepository.findById(targetRequest.getCourseId());
        if (optionalCourse.isPresent()) {
            target.setCourse(optionalCourse.get());
            target = targetRepository.save(target);
            return target;
        }
        return null;
    }

    @Override
    public boolean delete(long targetId) {
        Optional<Target> optional = targetRepository.findById(targetId);
        if (optional.isPresent()) {
            Target target = optional.get();
            targetRepository.delete(target);
            return true;
        }
        return false;
    }
}
