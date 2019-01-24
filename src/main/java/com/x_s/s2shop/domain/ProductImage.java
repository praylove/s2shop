package com.x_s.s2shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "t_product_image", schema = "mytmall")
public class ProductImage {
    
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "pid")
    private String pid;
    
    @Column(name = "file_id")
    private String fileId;
    
    @Column(name = "file_path")
    private String filePath;
    
    @Column(name = "primary")
    private boolean primary;
}
