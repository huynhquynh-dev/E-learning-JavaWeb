package com.myclass.controller;

import com.myclass.model.entity.Video;
import com.myclass.model.mapper.VideoMapper;
import com.myclass.model.request.VideoRequest;
import com.myclass.model.response.RestResult;
import com.myclass.model.response.VideoResponse;
import com.myclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    @Autowired
    VideoService videoService;

    @Autowired
    VideoMapper videoMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('role_admin')")
    public Object findAll() {
        RestResult<List<VideoResponse>> result = new RestResult<>();
        result.ok(videoMapper.toListDTO(videoService.findAll()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
    public Object findById(@PathVariable("id") long id) {
        RestResult<VideoResponse> result = new RestResult<>();
        Video permission = videoService.findById(id);
        if (permission != null) {
            VideoResponse permissionResponse = videoMapper.toDTO(permission);
            result.ok(permissionResponse);
        }
        else {
            result.fail();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('role_admin')")
    public Object save(@RequestBody VideoRequest videoRequest) {
        RestResult<VideoResponse> result = new RestResult<>();
        Video video = videoService.saveOrUpdate(videoRequest);
        if (video == null) {
            result.fail();
        }
        else {
            result.ok(videoMapper.toDTO(video));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('role_admin')")
    public Object update(@RequestBody VideoRequest videoRequest) {
        RestResult<VideoResponse> result = new RestResult<>();
        Video video = videoService.saveOrUpdate(videoRequest);
        if (video == null) {
            result.fail();
        }
        else {
            result.ok(videoMapper.toDTO(video));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('role_admin')")
    public Object delete(@PathVariable(value = "id") long id) {
        RestResult<String> result = new RestResult<>();
        if (videoService.delete(id)) {
            result.ok("Xóa thành công");
        }
        else {
            result.fail();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
