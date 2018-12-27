package com.x_s.s2shop.vo;

import lombok.Data;

@Data
public class SysUserVo {

    private String loginId;

    private String name;

    private String uid;

    private String roleIds;

    private int pageNo = 1;

    private int pageSize = 10;
}
