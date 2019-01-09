package com.x_s.s2shop.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_review", schema = "mytmall")
public class Review {
    private String reviewId;
    private String content;
    private Timestamp reviewDate;
    private String pid;
    private String uid;
    private String username;
    
    @Id
    @Column(name = "review_id")
    public String getReviewId() {
        return reviewId;
    }
    
    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }
    
    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Basic
    @Column(name = "review_date")
    public Timestamp getReviewDate() {
        return reviewDate;
    }
    
    public void setReviewDate(Timestamp reviewDate) {
        this.reviewDate = reviewDate;
    }
    
    @Basic
    @Column(name = "pid")
    public String getPid() {
        return pid;
    }
    
    public void setPid(String pid) {
        this.pid = pid;
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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Review review = (Review) o;
        
        if (reviewId != null ? !reviewId.equals(review.reviewId) : review.reviewId != null) return false;
        if (content != null ? !content.equals(review.content) : review.content != null) return false;
        if (reviewDate != null ? !reviewDate.equals(review.reviewDate) : review.reviewDate != null) return false;
        if (pid != null ? !pid.equals(review.pid) : review.pid != null) return false;
        if (uid != null ? !uid.equals(review.uid) : review.uid != null) return false;
        if (username != null ? !username.equals(review.username) : review.username != null) return false;
        
        return true;
    }
    
    @Override
    public int hashCode() {
        int result = reviewId != null ? reviewId.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (reviewDate != null ? reviewDate.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
