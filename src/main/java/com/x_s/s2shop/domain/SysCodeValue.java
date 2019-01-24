package com.x_s.s2shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "sys_code_value", schema = "mytmall")
public class SysCodeValue {

    @Id
    @Column(name = "value")
    private String value;

    @Basic
    @Column(name = "value_name")
    private String name;

    @Basic
    @Column(name = "code_type")
    private String codeType;

    @Basic
    @Column(name = "remark")
    private String remark;

    @Basic
    @Column(name = "status")
    private String status;
}
