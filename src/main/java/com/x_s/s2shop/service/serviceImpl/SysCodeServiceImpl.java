package com.x_s.s2shop.service.serviceImpl;

import com.github.wenhao.jpa.Sorts;
import com.github.wenhao.jpa.Specifications;
import com.x_s.s2shop.common.constant.CodeConstant;
import com.x_s.s2shop.common.exception.ParamException;
import com.x_s.s2shop.common.exception.ServiceException;
import com.x_s.s2shop.common.utils.DateUtils;
import com.x_s.s2shop.common.utils.HttpUtils;
import com.x_s.s2shop.domain.SysCode;
import com.x_s.s2shop.domain.SysCodeValue;
import com.x_s.s2shop.domain.SysUser;
import com.x_s.s2shop.repository.SysCodeRepository;
import com.x_s.s2shop.repository.SysCodeValueRepository;
import com.x_s.s2shop.service.SysCodeService;
import com.x_s.s2shop.vo.SysCodeValueVo;
import com.x_s.s2shop.vo.SysCodeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysCodeServiceImpl extends BaseServiceImpl<SysCode> implements SysCodeService {

    @Autowired
    private SysCodeRepository codeRepository;

    @Autowired
    private SysCodeValueRepository valueRepository;

    @Override
    public Page<SysCode> list(SysCodeVo vo) {
        Specification<SysCode> specification = Specifications.<SysCode>and()
                .like(StringUtils.isNotBlank(vo.getCodeType()), "codeType", "%" + vo.getCodeType() + "%")
                .build();
        Sort sort = Sorts.builder().desc("createTime").build();
        PageRequest page = PageRequest.of(vo.getPageNo() - 1, vo.getPageSize(), sort);
        return codeRepository.findAll(specification, page);
    }

    @Override
    public boolean hasChildren(String codeType) {
        return valueRepository.countByCodeType(codeType) > 0;
    }

    @Override
    public void save(String codeType, String remark) {
        SysCode code = new SysCode();
        code.setCodeType(codeType);
        code.setRemark(remark);
        save(code);
    }

    @Override
    @Transactional
    public void save(SysCode entity) {
        if (codeRepository.findByCodeType(entity.getCodeType()) != null){
            throw new ParamException("当前码表类型已存在！");
        }
        entity.setCreateTime(DateUtils.getCurrentTime());
        SysUser current = HttpUtils.getCurrentUser();
        entity.setCreator(current == null ? null : current.getId());
        entity.setStatus( CodeConstant.NORMAL_STATUS);
        codeRepository.save(entity);
    }

    @Override
    public void updateSelective(SysCode entity) throws ServiceException {
        super.updateSelective(entity, "codeType");
    }

    @Override
    public void delete(String codeTypes) throws ServiceException {
        if (codeTypes == null){
            throw new ParamException("删除项为空");
        }
        List<String> idList = Arrays.asList(codeTypes.split(","));
        List<String> list = idList.stream().filter(this::hasChildren).collect(Collectors.toList());
        if (!list.isEmpty()){
            String errorIds = String.join(",", list);
            throw new ParamException(errorIds + " 有码值，无法删除");
        }
        super.delete(codeTypes);
    }
}
