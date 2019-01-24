package com.x_s.s2shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "t_product", schema = "mytmall")
public class Product {
    
    @Id
    @Column(name = "pid")
    private String id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "cid")
    private String cid;
    
    @Column(name = "cname")
    private String cname;
    
    @Column(name = "createdate")
    private Timestamp createdate;
    
    @Column(name = "sale")
    private Integer sale;
    
    @Transient
    private Set<ProductImage> preview;
    
    @Transient
    private Set<ProductImage> details;
    
    public ProductImage getPrimaryPreview(){
        if (preview == null)
            return null;
        List<ProductImage> collect = preview.stream().filter(ProductImage::isPrimary).collect(Collectors.toList());
        if (collect.isEmpty())
            return null;
        return collect.get(0);
    }
}
