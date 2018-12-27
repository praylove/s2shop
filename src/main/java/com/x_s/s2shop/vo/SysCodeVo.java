package com.x_s.s2shop.vo;

import lombok.Data;

@Data
public class SysCodeVo {

    private String codeType;

    private int pageSize = 10;

    private int pageNo = 1;
}
