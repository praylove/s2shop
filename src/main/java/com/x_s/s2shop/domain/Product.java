package com.x_s.s2shop.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_product", schema = "mytmall")
public class Product {
    private int id;
    private String name;
    private String title;
    private Double orignalPrice;
    private Double promotionPrice;
    private Integer stock;
    private int cid;
    private String cname;
    private Timestamp createdate;
    private Integer sale;
    
    @Id
    @Column(name = "pid")
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Basic
    @Column(name = "orignalprice")
    public Double getOrignalPrice() {
        return orignalPrice;
    }
    
    public void setOrignalPrice(Double orignalPrice) {
        this.orignalPrice = orignalPrice;
    }
    
    @Basic
    @Column(name = "promotionprice")
    public Double getPromotionPrice() {
        return promotionPrice;
    }
    
    public void setPromotionPrice(Double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
    
    @Basic
    @Column(name = "stock")
    public Integer getStock() {
        return stock;
    }
    
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
    @Basic
    @Column(name = "cid")
    public int getCid() {
        return cid;
    }
    
    public void setCid(int cid) {
        this.cid = cid;
    }
    
    @Basic
    @Column(name = "cname")
    public String getCname() {
        return cname;
    }
    
    public void setCname(String cname) {
        this.cname = cname;
    }
    
    @Basic
    @Column(name = "createdate")
    public Timestamp getCreatedate() {
        return createdate;
    }
    
    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }
    
    @Basic
    @Column(name = "sale")
    public Integer getSale() {
        return sale;
    }
    
    public void setSale(Integer sale) {
        this.sale = sale;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Product product = (Product) o;
        
        if (id != product.id) return false;
        if (cid != product.cid) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (title != null ? !title.equals(product.title) : product.title != null) return false;
        if (orignalPrice != null ? !orignalPrice.equals(product.orignalPrice) : product.orignalPrice != null)
            return false;
        if (promotionPrice != null ? !promotionPrice.equals(product.promotionPrice) : product.promotionPrice != null)
            return false;
        if (stock != null ? !stock.equals(product.stock) : product.stock != null) return false;
        if (cname != null ? !cname.equals(product.cname) : product.cname != null) return false;
        if (createdate != null ? !createdate.equals(product.createdate) : product.createdate != null) return false;
        if (sale != null ? !sale.equals(product.sale) : product.sale != null) return false;
        
        return true;
    }
    
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (orignalPrice != null ? orignalPrice.hashCode() : 0);
        result = 31 * result + (promotionPrice != null ? promotionPrice.hashCode() : 0);
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        result = 31 * result + cid;
        result = 31 * result + (cname != null ? cname.hashCode() : 0);
        result = 31 * result + (createdate != null ? createdate.hashCode() : 0);
        result = 31 * result + (sale != null ? sale.hashCode() : 0);
        return result;
    }
}
