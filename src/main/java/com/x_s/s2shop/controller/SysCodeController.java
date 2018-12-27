package com.x_s.s2shop.controller;

import com.x_s.s2shop.common.entity.ResponseEntity;
import com.x_s.s2shop.common.exception.ParamException;
import com.x_s.s2shop.domain.SysCode;
import com.x_s.s2shop.domain.SysCodeValue;
import com.x_s.s2shop.service.BaseService;
import com.x_s.s2shop.service.SysCodeService;
import com.x_s.s2shop.vo.SysCodeValueVo;
import com.x_s.s2shop.vo.SysCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/code")
public class SysCodeController extends BaseController<SysCode>{

    @Autowired
    private SysCodeService codeService;

    @GetMapping("")
    public ResponseEntity list(SysCodeVo vo) {
        return ResponseEntity.ok(codeService.list(vo));
    }

}
