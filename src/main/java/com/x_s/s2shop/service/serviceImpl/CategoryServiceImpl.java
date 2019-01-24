package com.x_s.s2shop.service.serviceImpl;

import com.github.wenhao.jpa.Specifications;
import com.x_s.s2shop.common.exception.ParamException;
import com.x_s.s2shop.common.exception.ServiceException;
import com.x_s.s2shop.domain.Category;
import com.x_s.s2shop.repository.CategoryRepository;
import com.x_s.s2shop.service.CategoryService;
import com.x_s.s2shop.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {
    
    @Autowired
    private CategoryRepository repository;
    
    @Override
    public Page<Category> listRoots(CategoryVo categoryVo) {
        Specification<Category> specification = Specifications.<Category>and()
                .eq("parentId", "", null)
                .build();
//        Sort sort = Sorts.builder().desc("createTime").build();
        PageRequest page = PageRequest.of(categoryVo.getPageNo() - 1, categoryVo.getPageSize());
        return repository.findAll(specification, page);
    }
    
    @Override
    public Page<Category> listChildren(CategoryVo categoryVo, int id) {
        Specification<Category> specification = Specifications.<Category>and()
                .eq("parentId", id)
                .build();
//        Sort sort = Sorts.builder().desc("createTime").build();
        PageRequest page = PageRequest.of(categoryVo.getPageNo() - 1, categoryVo.getPageSize());
        return repository.findAll(specification, page);
    }
    
    @Override
    public void delete(String ids) {
        if (!StringUtils.hasText(ids)){
            throw new ParamException("删除项为空！");
        }
    
        try {
            repository.deleteOrphanByIds(ids);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
