package com.myclass.model.mapper.impl;

import com.myclass.model.entity.Target;
import com.myclass.model.mapper.TargetMapper;
import com.myclass.model.request.TargetRequest;
import com.myclass.model.response.TargetResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TargetMapperImpl implements TargetMapper {

    @Override
    public TargetResponse toDTO(Target target) {
        if (target == null) {
            return null;
        }
        TargetResponse targetResponse = new TargetResponse();
        targetResponse.setId(target.getId());
        targetResponse.setTitle(target.getTitle());
        targetResponse.setOrderIndex(target.getOrderIndex());
        targetResponse.setEnabled(target.isEnabled());
        targetResponse.setCourseId(target.getCourse().getId());
        return targetResponse;
    }

    @Override
    public List<TargetResponse> toListDTO(List<Target> targets) {
        if (targets == null) {
            return null;
        }
        List<TargetResponse> targetResponses = new ArrayList<>();
        for (Target target : targets) {
            targetResponses.add(toDTO(target));
        }
        return targetResponses;
    }

    @Override
    public Target toEntity(Target target, TargetRequest targetRequest) {
        if (targetRequest == null) {
            return null;
        }
        if (targetRequest.getId() > 0) {
            target.setId(targetRequest.getId());
        }
        target.setTitle(targetRequest.getTitle());
        target.setOrderIndex(targetRequest.getOrderIndex());
        target.setEnabled(targetRequest.isEnabled());
        return target;
    }
}
