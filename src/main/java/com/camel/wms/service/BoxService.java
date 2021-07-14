package com.camel.wms.service;

import com.camel.wms.model.Box;
import com.camel.wms.repository.BoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxService {
    @Autowired
    private BoxRepository boxRepository;

    public List<Box> getAllBoxes() {
        return boxRepository.getAllBoxes();
    }

    public List<Box> findAll() {
        return boxRepository.findAll();
    }

    public Box findById(Long boxId) {
        return boxRepository.findById(boxId).orElseThrow();
    }
}
