package com.x_s.s2shop.vo;

import lombok.Data;

@Data
public class SysCodeValueVo {

    private String codeType;

    private String value;

    private String name;

    private String remark;

    private int pageNo = 1;

    private int pageSize = 10;

}
