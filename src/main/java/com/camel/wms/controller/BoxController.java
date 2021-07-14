package com.camel.wms.controller;


import com.bsuir.WarehouseManagementSystem.model.Box;
import com.bsuir.WarehouseManagementSystem.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class BoxController {

    @Autowired
    private BoxService boxService;

    @GetMapping("/boxes")
    public String getBoxes(Model model){
        model.addAttribute("boxes",boxService.getAllBoxes());

        return "boxesList";
    }

    @PostMapping("/findBox")
    public String findBox(Model model,@RequestParam String filter){

        if(filter.isEmpty()){
            model.addAttribute("boxes",boxService.getAllBoxes());
        }
        else{
            try{
                Box box = boxService.findById(Long.valueOf(filter));
                model.addAttribute("boxes",box);
            }
            catch (NoSuchElementException ex){
                List<Box> emptyList = new ArrayList<Box>();
                model.addAttribute("boxes",emptyList);
            }
        }

        return "boxesList";
    }
}
