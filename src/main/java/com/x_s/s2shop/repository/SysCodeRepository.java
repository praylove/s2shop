package com.x_s.s2shop.repository;

import com.x_s.s2shop.domain.SysCode;

public interface SysCodeRepository extends BaseRepository<SysCode, String> {
    SysCode findByCodeType(String codeType);
}
