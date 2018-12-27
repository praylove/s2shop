package com.x_s.s2shop.domain;

import com.x_s.s2shop.common.constant.CodeConstant;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "sys_user", schema = "mytmall", catalog = "")
public class SysUser {

    /**
     * 用户ID
     */
    @Id
    @Column(name = "uid")
    private String id;

    /**
     * 登录名
     */
    @Basic
    @Column(name = "login_id")
    @NotNull(message = "登录名不能为空！")
    private String loginId;

    /**
     * 用户名称
     */
    @Basic
    @Column(name = "name")
    @NotNull(message = "用户名不能为空！")
    private String name;

    /**
     * 登录密码
     */
    @Basic
    @Column(name = "password")
    private String password;

    /**
     * 盐
     */
    @Basic
    @Column(name = "salt")
    private String salt;

    /**
     * 用户状态
     */
    @Basic
    @Column(name = "status")
    private String status;

    /**
     * 联系电话
     */
    @Basic
    @Column(name = "phone")
    private String phone;

    /**
     * 创建时间
     */
    @Basic
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Basic
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 头像地址
     */
    @Basic()
    @Column(name = "avatar")
    @NotNull(message = "头像不能为空！")
    private String avatar;

    /**
     * 账户未过期
     */
    @Basic()
    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;

    /**
     * 账户未锁定
     */
    @Basic()
    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    /**
     * 密码未过期
     */
    @Basic()
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;

    @ManyToMany(targetEntity = SysRole.class, fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "uid", referencedColumnName = "uid")},
        inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    private Set<SysRole> roles;

    public boolean isEnable(){
        return CodeConstant.NORMAL_STATUS.equals(this.getStatus());
    }

}
