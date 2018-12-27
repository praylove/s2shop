package com.x_s.s2shop.vo;

import lombok.Data;

@Data
public class SysMenuVo  {

    private String menuName;

    private String[] roleId;

    private String[] type;

    private int pageNo = 1;

    private int pageSize = 10;
}
