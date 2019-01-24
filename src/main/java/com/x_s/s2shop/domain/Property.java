package com.x_s.s2shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "t_property", schema = "mytmall")
public class Property {
    
    @Id
    @Column(name = "property_id")
    private String propertyId;
    
    @Column(name = "property_name")
    private String propertyName;
    
    @Column(name = "cid")
    private String cid;
    
}
