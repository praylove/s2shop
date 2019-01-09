package com.x_s.s2shop.controller;

import com.x_s.s2shop.common.entity.ResponseEntity;
import com.x_s.s2shop.common.exception.ParamException;
import com.x_s.s2shop.domain.SysUser;
import com.x_s.s2shop.service.SysUserService;
import com.x_s.s2shop.vo.SysUserVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController<SysUser> {
    @Autowired
    private SysUserService userService;

    @GetMapping
    public ResponseEntity list(SysUserVo userVo){
        return ResponseEntity.ok(userService.list(userVo));
    }

    @GetMapping("/role")
    public ResponseEntity listRoles(String uid){
        SysUser user = userService.findById(uid).orElseThrow(() -> new ParamException("当前用户不存在！"));
        return ResponseEntity.ok(user.getRoles());
    }

    @PostMapping("/reset")
    public ResponseEntity reset(@RequestBody SysUserVo userVo){
        userService.resetPassword(userVo.getUid());
        return ResponseEntity.ok();
    }

    @PostMapping("/role")
    public ResponseEntity addRole(@RequestBody SysUserVo userVo){
        userService.addRole(userVo.getUid(), userVo.getRoleIds().split(","));
        return ResponseEntity.ok();
    }

    @DeleteMapping("/role")
    public ResponseEntity removeRole(@RequestBody SysUserVo userVo){
        userService.removeRole(userVo.getUid(), userVo.getRoleIds().split(","));
        return ResponseEntity.ok();
    }

}
