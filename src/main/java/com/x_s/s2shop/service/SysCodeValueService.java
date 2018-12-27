package com.x_s.s2shop.service;

import com.x_s.s2shop.domain.SysCodeValue;
import com.x_s.s2shop.vo.SysCodeValueVo;
import org.springframework.data.domain.Page;

public interface SysCodeValueService extends BaseService<SysCodeValue> {

    Page<SysCodeValue> list(SysCodeValueVo valueVo);

}
