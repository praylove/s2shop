package com.x_s.s2shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "t_property_value", schema = "mytmall")
public class PropertyValue {
    
    @Id
    @Column(name = "property_value_id")
    private String propertyValueId;
    
    @Basic
    @Column(name = "property_value")
    private String propertyValue;
    
    @Basic
    @Column(name = "property_name")
    private String propertyName;
    
    @Basic
    @Column(name = "pid")
    private String pid;
    
    @Basic
    @Column(name = "property_id")
    private String propertyId;
    
}
