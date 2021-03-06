package com.x_s.s2shop.controller;

import com.x_s.s2shop.common.constant.CodeConstant;
import com.x_s.s2shop.common.entity.ResponseEntity;
import com.x_s.s2shop.domain.SysMenu;
import com.x_s.s2shop.service.SysMenuService;
import com.x_s.s2shop.vo.SysMenuVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/menu")
public class SysMenuController extends BaseController<SysMenu>{
    @Autowired
    private SysMenuService menuService;

    @GetMapping
    public ResponseEntity findAll(SysMenuVo menuVo){
        return ResponseEntity.ok(menuService.list(menuVo));
    }
    
    /**
     * 获取后台菜单和后台按钮的结构树
     */
    @GetMapping("/tree")
    public ResponseEntity getTree(){
        return ResponseEntity.ok(menuService.getTree(CodeConstant.BACK_MENU, CodeConstant.BACK_BUTTON));
    }
    
    /**
     * 获取后台菜单的结构树
     */
    @GetMapping("/treeMenu")
    public ResponseEntity getTreeMenu(){
        return ResponseEntity.ok(menuService.getUserTree(CodeConstant.BACK_MENU));
    }

    
}
