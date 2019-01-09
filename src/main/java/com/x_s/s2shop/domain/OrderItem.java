package com.x_s.s2shop.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "t_order_item", schema = "mytmall")
public class OrderItem {
    private String id;
    private Integer number;
    private BigDecimal prices;
    private String prouctId;
    private String orderId;
    
    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Basic
    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }
    
    public void setNumber(Integer number) {
        this.number = number;
    }
    
    @Basic
    @Column(name = "prices")
    public BigDecimal getPrices() {
        return prices;
    }
    
    public void setPrices(BigDecimal prices) {
        this.prices = prices;
    }
    
    @Basic
    @Column(name = "pid")
    public String getProuctId() {
        return prouctId;
    }
    
    public void setProuctId(String prouctId) {
        this.prouctId = prouctId;
    }
    
    @Basic
    @Column(name = "oid")
    public String getOrderId() {
        return orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        OrderItem orderItem = (OrderItem) o;
        
        if (id != null ? !id.equals(orderItem.id) : orderItem.id != null) return false;
        if (number != null ? !number.equals(orderItem.number) : orderItem.number != null) return false;
        if (prices != null ? !prices.equals(orderItem.prices) : orderItem.prices != null) return false;
        if (prouctId != null ? !prouctId.equals(orderItem.prouctId) : orderItem.prouctId != null) return false;
        if (orderId != null ? !orderId.equals(orderItem.orderId) : orderItem.orderId != null) return false;
        
        return true;
    }
    
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (prices != null ? prices.hashCode() : 0);
        result = 31 * result + (prouctId != null ? prouctId.hashCode() : 0);
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        return result;
    }
}
