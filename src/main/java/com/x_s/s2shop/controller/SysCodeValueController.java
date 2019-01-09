package com.x_s.s2shop.controller;

import com.x_s.s2shop.common.entity.ResponseEntity;
import com.x_s.s2shop.domain.SysCodeValue;
import com.x_s.s2shop.service.SysCodeValueService;
import com.x_s.s2shop.vo.SysCodeValueVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code/value")
public class SysCodeValueController extends BaseController<SysCodeValue> {

    @Autowired
    private SysCodeValueService valueService;

    @GetMapping
    public ResponseEntity list(SysCodeValueVo valueVo) {
        return ResponseEntity.ok(valueService.list(valueVo));
    }
}
