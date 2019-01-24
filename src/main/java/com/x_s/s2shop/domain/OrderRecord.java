package com.x_s.s2shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "t_order_record", schema = "mytmall")
public class OrderRecord {
    
    @Id
    @Column(name = "id")
    private String id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
    
    @Column(name = "order_status")
    private String orderStatus;
    
    @Column(name = "update_time")
    private Timestamp updateTime;
}
