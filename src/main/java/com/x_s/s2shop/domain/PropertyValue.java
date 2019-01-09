package com.x_s.s2shop.domain;

import javax.persistence.*;

@Entity
@Table(name = "t_property_value", schema = "mytmall")
public class PropertyValue {
    private String propertyValueId;
    private String propertyValue;
    private String propertyName;
    private int pid;
    private String propertyId;
    
    @Id
    @Column(name = "property_value_id")
    public String getPropertyValueId() {
        return propertyValueId;
    }
    
    public void setPropertyValueId(String propertyValueId) {
        this.propertyValueId = propertyValueId;
    }
    
    @Basic
    @Column(name = "property_value")
    public String getPropertyValue() {
        return propertyValue;
    }
    
    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
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
    @Column(name = "pid")
    public int getPid() {
        return pid;
    }
    
    public void setPid(int pid) {
        this.pid = pid;
    }
    
    @Basic
    @Column(name = "property_id")
    public String getPropertyId() {
        return propertyId;
    }
    
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        PropertyValue that = (PropertyValue) o;
        
        if (pid != that.pid) return false;
        if (propertyValueId != null ? !propertyValueId.equals(that.propertyValueId) : that.propertyValueId != null)
            return false;
        if (propertyValue != null ? !propertyValue.equals(that.propertyValue) : that.propertyValue != null)
            return false;
        if (propertyName != null ? !propertyName.equals(that.propertyName) : that.propertyName != null) return false;
        if (propertyId != null ? !propertyId.equals(that.propertyId) : that.propertyId != null) return false;
        
        return true;
    }
    
    @Override
    public int hashCode() {
        int result = propertyValueId != null ? propertyValueId.hashCode() : 0;
        result = 31 * result + (propertyValue != null ? propertyValue.hashCode() : 0);
        result = 31 * result + (propertyName != null ? propertyName.hashCode() : 0);
        result = 31 * result + pid;
        result = 31 * result + (propertyId != null ? propertyId.hashCode() : 0);
        return result;
    }
}
