package com.x_s.s2shop.controller;

import com.x_s.s2shop.common.entity.ResponseEntity;
import com.x_s.s2shop.common.exception.ParamException;
import com.x_s.s2shop.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

public abstract class BaseController<T> {

    @Autowired
    private BaseService<T> service;

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody T object, BindingResult result) {
        if (result.hasErrors()){
            throw new ParamException(result.getFieldError().getDefaultMessage());
        }
        service.save(object);
        return ResponseEntity.ok();
    }

    @PutMapping("")
    public ResponseEntity update(@RequestBody T object, BindingResult result){
        if (result.hasErrors()){
            throw new ParamException(result.getFieldError().getDefaultMessage());
        }
        service.updateSelective(object);
        return ResponseEntity.ok();
    }

    @DeleteMapping("")
    public ResponseEntity delete(@RequestBody String ids){
        System.out.println(ids);
//        service.delete(ids);
        return ResponseEntity.ok();
    }

}
