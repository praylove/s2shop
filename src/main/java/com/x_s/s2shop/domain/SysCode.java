package com.x_s.s2shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "sys_code", schema = "mytmall", catalog = "")
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
