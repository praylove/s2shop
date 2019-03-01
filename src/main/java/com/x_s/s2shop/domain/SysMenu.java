package com.x_s.s2shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "sys_menu", schema = "mytmall")
public class SysMenu {

    @Id
    @Column(name = "menu_id")
    private String id;

    @Basic
    @Column(name = "menu_name")
    private String menuName;

    @Basic
    @Column(name = "menu_order")
    private int order;

    @Basic
    @Column(name = "icon")
    private String icon;

    @Basic
    @Column(name = "parent_id")
    private String parentId;

    @Basic
    @Column(name = "parent_name")
    private String parentName;

    @Basic
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "path")
    private String path;

    @Basic
    @Column(name = "create_time")
    private Date createTime;

    @Basic
    @Column(name = "update_time")
    private Date updateTime;

    @Basic
    @Column(name = "remark")
    private String remark;

    @Basic
    @Column(name = "status")
    private String status;

    @Basic
    @Column(name = "hidden")
    private boolean hidden;

    @Transient
    private List<SysMenu> children;

    public SysMenu(){
    }

    public SysMenu(String id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysMenu sysMenu = (SysMenu) o;

        return id != null ? id.equals(sysMenu.id) : sysMenu.id == null;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
