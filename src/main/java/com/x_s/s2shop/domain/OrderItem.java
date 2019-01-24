package com.x_s.s2shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "t_order_item", schema = "mytmall")
public class OrderItem {
    
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "number")
    private Integer number;
    
    @Column(name = "prices")
    private BigDecimal prices;
    
    @Column(name = "pid")
    private String prouctId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oid")
    @JsonIgnore
    private Order order;
    
}
