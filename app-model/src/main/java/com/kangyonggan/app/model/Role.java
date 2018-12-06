package com.kangyonggan.app.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Table(name = "tb_role")
@Data
public class Role implements Serializable {
    /**
     * 主键, 自增
     */
    @Id
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 角色代码
     */
    @Column(name = "role_code")
    private String roleCode;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 逻辑删除
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}