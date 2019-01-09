package com.x_s.s2shop.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sys_code", schema = "mytmall")
public class SysCode {

    @Id
    @Column(name = "code_type")
    private String codeType;

    @Basic
    @Column(name = "create_time")
    private Date createTime;

    @Basic
    @Column(name = "creator")
    private String creator;

    @Basic
    @Column(name = "status")
    private String status;

    @Basic
    @Column(name = "update_time")
    private Date updateTime;

    @Basic
    @Column(name = "remark")
    private String remark;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "value")
//    @JsonIgnore
//    private Set<SysCodeValue> values;
}
