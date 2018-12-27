package com.x_s.s2shop.service;

import com.x_s.s2shop.domain.SysCode;
import com.x_s.s2shop.vo.SysCodeVo;
import org.springframework.data.domain.Page;

public interface SysCodeService extends BaseService<SysCode>{

    Page<SysCode> list(SysCodeVo vo);

    boolean hasChildren(String codeId);

    void save(String codeType, String remark);

}
