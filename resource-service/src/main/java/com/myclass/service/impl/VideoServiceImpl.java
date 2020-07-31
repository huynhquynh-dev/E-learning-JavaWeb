package com.myclass.service.impl;

import com.myclass.model.entity.Course;
import com.myclass.model.entity.Target;
import com.myclass.model.entity.Video;
import com.myclass.model.mapper.VideoMapper;
import com.myclass.model.request.VideoRequest;
import com.myclass.repository.CourseRepository;
import com.myclass.repository.VideoRepository;
import com.myclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    VideoMapper videoMapper;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    @Override
    public Video findById(long videoId) {
        Optional<Video> optional = videoRepository.findById(videoId);
        return optional.orElse(null);
    }

    @Override
    public Video saveOrUpdate(VideoRequest videoRequest) {
        Video video = null;
        if (videoRequest.getId() == 0) {
            video = new Video();
        }
        else {
            Optional<Video> optional = videoRepository.findById(videoRequest.getId());
            if (optional.isPresent()){
                video = optional.get();
            }
        }
        video = videoMapper.toEntity(video, videoRequest);
        Optional<Course> optionalCourse = courseRepository.findById(videoRequest.getCourseId());
        if (optionalCourse.isPresent()) {
            video.setCourse(optionalCourse.get());
            video = videoRepository.save(video);
            return video;
        }
        return null;
    }

    @Override
    public boolean delete(long videoId) {
        Optional<Video> optional = videoRepository.findById(videoId);
        if (optional.isPresent()) {
            Video video = optional.get();
            videoRepository.delete(video);
            return true;
        }
        return false;
    }
}
