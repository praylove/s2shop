package com.x_s.s2shop.controller;

import com.x_s.s2shop.common.entity.ResponseEntity;
import com.x_s.s2shop.domain.Category;
import com.x_s.s2shop.service.CategoryService;
import com.x_s.s2shop.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController<Category>{
    
    @Autowired
    private CategoryService categoryService;
    
    public ResponseEntity list(@RequestBody CategoryVo vo){
        return ResponseEntity.ok(categoryService.listRoots(vo));
    }
    
}
