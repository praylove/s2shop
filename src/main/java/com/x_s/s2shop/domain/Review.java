package com.x_s.s2shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "t_review", schema = "mytmall")
public class Review {
    
    @Id
    @Column(name = "review_id")
    private String reviewId;
    
    @Column(name = "content")
    private String content;
    
    @Column(name = "review_date")
    private Timestamp reviewDate;
    
    @Column(name = "pid")
    private String pid;
    
    @Column(name = "uid")
    private String uid;
    
    @Column(name = "username")
    private String username;
}
