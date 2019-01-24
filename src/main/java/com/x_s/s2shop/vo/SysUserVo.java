package com.x_s.s2shop.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

@Getter
@Setter
public class SysUserVo extends PageVo {

    private String loginId;

    private String name;

    private String uid;

    private String roleIds;

}
