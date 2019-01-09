package com.x_s.s2shop.service.serviceImpl;

import com.github.wenhao.jpa.Specifications;
import com.x_s.s2shop.common.constant.CodeConstant;
import com.x_s.s2shop.domain.SysCodeValue;
import com.x_s.s2shop.repository.SysCodeValueRepository;
import com.x_s.s2shop.service.SysCodeValueService;
import com.x_s.s2shop.vo.SysCodeValueVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SysCodeValueServiceImpl extends BaseServiceImpl<SysCodeValue> implements SysCodeValueService {

    @Autowired
    private SysCodeValueRepository valueRepository;

    private static final String ID_FIELD_NAME = "value";

    public Page<SysCodeValue> list(SysCodeValueVo valueVo) {
        Specification<SysCodeValue> specification = Specifications.<SysCodeValue>and()
                .eq(StringUtils.hasText(valueVo.getCodeType()), "codeType", valueVo.getCodeType())
                .like(StringUtils.hasText(valueVo.getName()), "name", "%" + valueVo.getName() + "%")
                .like(StringUtils.hasText(valueVo.getValue()), "value", "%" + valueVo.getName() + "%")
                .like(StringUtils.hasText(valueVo.getRemark()), "remark", "%" + valueVo.getName() + "%")
                .build();
        PageRequest page = PageRequest.of(valueVo.getPageNo() - 1, valueVo.getPageSize());
        return valueRepository.findAll(specification, page);
    }

    @Override
    public void save(SysCodeValue entity) {
        entity.setStatus(CodeConstant.NORMAL_STATUS);
        valueRepository.save(entity);
    }

    @Override
    public void updateSelective(SysCodeValue entity)  {
        super.updateSelective(entity, ID_FIELD_NAME);
    }
}
