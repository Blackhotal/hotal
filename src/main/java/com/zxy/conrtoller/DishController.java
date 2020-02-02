package com.zxy.conrtoller;

import com.zxy.service.DishService;
import com.zxy.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DishCOntroller
 * @author black
 */
@RestController
@CrossOrigin
@RequestMapping("/Dish")
public class DishController {
    @Autowired
    private DishService service;

    @RequestMapping("/findAll")
    public ResponseEntity findAll(){
        try {
            return ResponseEntity.success(service.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.error(500,"Dish,FindALl,Error");
        }
    }
}
