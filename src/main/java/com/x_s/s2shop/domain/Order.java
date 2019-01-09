package com.x_s.s2shop.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_order", schema = "mytmall")
public class Order {
    private String id;
    private String orderCode;
    private String status;
    private String uid;
    private Timestamp createTime;
    private String address;
    private String post;
    private String telnumber;
    private String receiver;
    private String userMessage;
    private Boolean lock;
    
    @Id
    @Column(name = "oid")
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Basic
    @Column(name = "order_code")
    public String getOrderCode() {
        return orderCode;
    }
    
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
    
    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Basic
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    
    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Basic
    @Column(name = "post")
    public String getPost() {
        return post;
    }
    
    public void setPost(String post) {
        this.post = post;
    }
    
    @Basic
    @Column(name = "telnumber")
    public String getTelnumber() {
        return telnumber;
    }
    
    public void setTelnumber(String telnumber) {
        this.telnumber = telnumber;
    }
    
    @Basic
    @Column(name = "receiver")
    public String getReceiver() {
        return receiver;
    }
    
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    
    @Basic
    @Column(name = "user_message")
    public String getUserMessage() {
        return userMessage;
    }
    
    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
    
    @Basic
    @Column(name = "lock")
    public Boolean getLock() {
        return lock;
    }
    
    public void setLock(Boolean lock) {
        this.lock = lock;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Order order = (Order) o;
        
        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (orderCode != null ? !orderCode.equals(order.orderCode) : order.orderCode != null) return false;
        if (status != null ? !status.equals(order.status) : order.status != null) return false;
        if (uid != null ? !uid.equals(order.uid) : order.uid != null) return false;
        if (createTime != null ? !createTime.equals(order.createTime) : order.createTime != null) return false;
        if (address != null ? !address.equals(order.address) : order.address != null) return false;
        if (post != null ? !post.equals(order.post) : order.post != null) return false;
        if (telnumber != null ? !telnumber.equals(order.telnumber) : order.telnumber != null) return false;
        if (receiver != null ? !receiver.equals(order.receiver) : order.receiver != null) return false;
        if (userMessage != null ? !userMessage.equals(order.userMessage) : order.userMessage != null) return false;
        if (lock != null ? !lock.equals(order.lock) : order.lock != null) return false;
        
        return true;
    }
    
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderCode != null ? orderCode.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (post != null ? post.hashCode() : 0);
        result = 31 * result + (telnumber != null ? telnumber.hashCode() : 0);
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        result = 31 * result + (userMessage != null ? userMessage.hashCode() : 0);
        result = 31 * result + (lock != null ? lock.hashCode() : 0);
        return result;
    }
}
