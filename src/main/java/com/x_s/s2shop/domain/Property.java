package com.x_s.s2shop.domain;

import javax.persistence.*;

@Entity
@Table(name = "t_property", schema = "mytmall")
public class Property {
    private String propertyId;
    private String propertyName;
    private int cid;
    
    @Id
    @Column(name = "property_id")
    public String getPropertyId() {
        return propertyId;
    }
    
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }
    
    @Basic
    @Column(name = "property_name")
    public String getPropertyName() {
        return propertyName;
    }
    
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    
    @Basic
    @Column(name = "cid")
    public int getCid() {
        return cid;
    }
    
    public void setCid(int cid) {
        this.cid = cid;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Property property = (Property) o;
        
        if (cid != property.cid) return false;
        if (propertyId != null ? !propertyId.equals(property.propertyId) : property.propertyId != null) return false;
        if (propertyName != null ? !propertyName.equals(property.propertyName) : property.propertyName != null)
            return false;
        
        return true;
    }
    
    @Override
    public int hashCode() {
        int result = propertyId != null ? propertyId.hashCode() : 0;
        result = 31 * result + (propertyName != null ? propertyName.hashCode() : 0);
        result = 31 * result + cid;
        return result;
    }
}
