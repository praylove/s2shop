package com.x_s.s2shop.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_category", schema = "mytmall")
public class Category {
    
    @Id
    @Column(name = "cid")
    private String id;
    
    @Basic
    @Column(name = "cname")
    private String name;
    
    @Basic
    @Column(name = "cimage")
    private String image;
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Category category = (Category) o;
        
        if (id != null ? !id.equals(category.id) : category.id != null) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        if (image != null ? !image.equals(category.image) : category.image != null) return false;
        
        return true;
    }
    
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
