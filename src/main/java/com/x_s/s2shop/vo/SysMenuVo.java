package com.x_s.s2shop.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysMenuVo extends PageVo {

    private String menuName;

    private String[] roleId;

    private String[] type;
}
