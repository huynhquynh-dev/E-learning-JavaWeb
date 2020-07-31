package com.myclass.model.mapper.impl;

import com.myclass.model.entity.Video;
import com.myclass.model.mapper.VideoMapper;
import com.myclass.model.request.VideoRequest;
import com.myclass.model.response.VideoResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoMapperImpl implements VideoMapper {

    @Override
    public VideoResponse toDTO(Video video) {
        if(video == null) {
            return null;
        }
        VideoResponse videoResponse = new VideoResponse();
        videoResponse.setId(video.getId());
        videoResponse.setTitle(video.getTitle());
        videoResponse.setTimeCount(video.getTimeCount());
        videoResponse.setUrl(video.getUrl());
        videoResponse.setEnabled(video.isEnabled());
        videoResponse.setCourseId(video.getCourse().getId());
        return videoResponse;
    }

    @Override
    public List<VideoResponse> toListDTO(List<Video> videos) {
        if (videos == null) {
            return null;
        }
        List<VideoResponse> videoResponses = new ArrayList<>();
        for (Video video : videos) {
            videoResponses.add(toDTO(video));
        }
        return videoResponses;
    }

    @Override
    public Video toEntity(Video video, VideoRequest videoRequest) {
        if (videoRequest == null) {
            return null;
        }
        if (videoRequest.getId() != 0) {
            video.setId(videoRequest.getId());
        }
        video.setTitle(videoRequest.getTitle());
        video.setTimeCount(videoRequest.getTimeCount());
        video.setUrl(videoRequest.getUrl());
        video.setEnabled(videoRequest.isEnabled());
        return video;
    }
}
