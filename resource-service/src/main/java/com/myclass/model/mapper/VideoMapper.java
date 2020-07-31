package com.myclass.model.mapper;

import com.myclass.model.entity.Video;
import com.myclass.model.request.VideoRequest;
import com.myclass.model.response.VideoResponse;

import java.util.List;

public interface VideoMapper {

    VideoResponse toDTO(Video video);

    List<VideoResponse> toListDTO(List<Video> videos);

    Video toEntity(Video video, VideoRequest videoRequest);
}
