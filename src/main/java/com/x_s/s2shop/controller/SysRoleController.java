package com.x_s.s2shop.controller;

import com.x_s.s2shop.common.entity.ResponseEntity;
import com.x_s.s2shop.common.exception.ParamException;
import com.x_s.s2shop.domain.SysRole;
import com.x_s.s2shop.service.SysRoleService;
import com.x_s.s2shop.vo.SysRoleVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/role")
public class SysRoleController extends BaseController<SysRole>{

    @Autowired
    private SysRoleService roleService;

    @GetMapping
    public ResponseEntity findAll(SysRoleVo roleVo){
        return ResponseEntity.ok(roleService.list(roleVo));
    }

    @GetMapping("/all")
    public ResponseEntity findAllWithoutPage(SysRoleVo roleVo){
        System.out.println(roleVo);
        return ResponseEntity.ok(roleService.listWithoutPage(roleVo));
    }


    @GetMapping("/menu")
    public ResponseEntity listRoles(String rid){
        SysRole role = roleService.findById(rid).orElseThrow(() -> new ParamException("当前角色不存在！"));
        return ResponseEntity.ok(role.getMenus());
    }

    @PostMapping("/menu")
    public ResponseEntity updateRole(@RequestBody SysRoleVo roleVo){
        roleService.updateMeanu(roleVo.getRid(), roleVo.getMenuIds().split(","));
        return ResponseEntity.ok();
    }
}
