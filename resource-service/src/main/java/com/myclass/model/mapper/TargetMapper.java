package com.myclass.model.mapper;

import com.myclass.model.entity.Target;
import com.myclass.model.request.TargetRequest;
import com.myclass.model.response.TargetResponse;

import java.util.List;

public interface TargetMapper {

    TargetResponse toDTO(Target target);

    List<TargetResponse> toListDTO(List<Target> targets);

    Target toEntity(Target target, TargetRequest targetRequest);
}
