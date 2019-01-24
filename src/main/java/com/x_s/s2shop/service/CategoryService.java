package com.x_s.s2shop.service;

import com.x_s.s2shop.domain.Category;
import com.x_s.s2shop.vo.CategoryVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService extends BaseService<Category>{
    
    Page<Category> listRoots(CategoryVo categoryVo);
    
    Page<Category> listChildren(CategoryVo categoryVo, int id);
    
}
