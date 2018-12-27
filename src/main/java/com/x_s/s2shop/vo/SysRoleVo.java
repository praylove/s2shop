package com.x_s.s2shop.vo;

import lombok.Data;

@Data
public class SysRoleVo {

    private String rid;

    private String roleName;

    private String menuIds;

    private String remark;

    private int pageNo = 1;

    private int pageSize = 10;

}
