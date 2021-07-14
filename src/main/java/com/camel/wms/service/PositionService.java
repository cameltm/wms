package com.camel.wms.service;

import com.camel.wms.model.Position;
import com.camel.wms.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService {
    @Autowired
    PositionRepository positionRepository;

    public void reducePositionFullness(Long positionId,Integer amount){
        Position position = positionRepository.findById(positionId).orElseThrow();
        position.setFullness(position.getFullness() - amount);
        positionRepository.save(position);
    }
}
