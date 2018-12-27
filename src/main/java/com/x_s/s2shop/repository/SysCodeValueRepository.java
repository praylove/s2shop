package com.x_s.s2shop.repository;

import com.x_s.s2shop.domain.SysCodeValue;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface SysCodeValueRepository extends BaseRepository<SysCodeValue, String> {

    List<SysCodeValue> findByCodeType(String codeId, PageRequest page);

    int countByCodeType(String codeId);
}
