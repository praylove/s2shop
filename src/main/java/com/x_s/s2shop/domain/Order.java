package com.x_s.s2shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_order", schema = "mytmall")
public class Order {
    
    @Id
    @Column(name = "oid")
    private String id;
    
    @Column(name = "order_code")
    private String orderCode;
    
    @Column(name = "status")
    private String status;
    
    @OneToOne
    @JoinColumn(name = "uid",referencedColumnName = "uid")
    private SysUser user;
    
    @Column(name = "create_time")
    private Date createTime;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "post")
    private String post;
    
    @Column(name = "telnumber")
    private String telnumber;
    
    @Column(name = "receiver")
    private String receiver;
    
    @Column(name = "user_message")
    private String userMessage;
    
    @Column(name = "lock")
    private Boolean lock;
    
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    
    @OneToMany(mappedBy = "order", orphanRemoval = true)
    private Set<OrderItem> orderItems;
    
    @OneToMany(mappedBy = "order", orphanRemoval = true)
    private Set<OrderRecord> records;
}
