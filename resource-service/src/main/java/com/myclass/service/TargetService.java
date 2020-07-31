package com.myclass.service;

import com.myclass.model.entity.Target;
import com.myclass.model.request.TargetRequest;

import java.util.List;

public interface TargetService {

    List<Target> findAll();

    Target findById(long targetId);

    Target saveOrUpdate(TargetRequest targetRequest);

    boolean delete(long targetId);
}
