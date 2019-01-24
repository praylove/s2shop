package com.x_s.s2shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "t_product_specification", schema = "mytmall")
public class ProductSpecification {
    
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "stock")
    private Integer stock;
    
    @Column(name = "orignal_price")
    private BigDecimal orignalPrice;
    
    @Column(name = "promote_price")
    private BigDecimal promotePrice;
    
    @Column(name = "pid")
    private String pid;
    
}
