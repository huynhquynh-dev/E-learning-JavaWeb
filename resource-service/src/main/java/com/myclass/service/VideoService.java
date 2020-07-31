package com.myclass.service;

import com.myclass.model.entity.Video;
import com.myclass.model.request.VideoRequest;

import java.util.List;

public interface VideoService {

    List<Video> findAll();

    Video findById(long videoId);

    Video saveOrUpdate(VideoRequest videoRequest);

    boolean delete(long videoId);
}
