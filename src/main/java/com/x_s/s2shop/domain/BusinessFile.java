package com.x_s.s2shop.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "bus_file")
public class BusinessFile {

    @Id
    @Column(name = "file_id")
    private String id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "file_type")
    private String fileType;

    private String path;

    private String suffix;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;

    private int used;

}
