package com.x_s.s2shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "t_category", schema = "mytmall")
public class Category {
    
    @Id
    @Column(name = "cid")
    private String id;
    
    @Column(name = "cname")
    private String name;
    
    @Column(name = "parent_id")
    private String parentId;
    
    @OneToOne
    @JoinColumn(name = "cimage",referencedColumnName = "file_id")
    private BusinessFile image;
    
}
