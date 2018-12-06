package com.kangyonggan.app.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Table(name = "tb_menu")
@Data
public class Menu implements Serializable {
    /**
     * 主键, 自增
     */
    @Id
    @Column(name = "menu_id")
    private Long menuId;

    /**
     * 菜单代码
     */
    @Column(name = "menu_code")
    private String menuCode;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 父菜单代码
     */
    @Column(name = "parent_code")
    private String parentCode;

    /**
     * 菜单地址
     */
    private String url;

    /**
     * 菜单排序(从0开始)
     */
    private Integer sort;

    /**
     * 菜单图标的样式
     */
    private String icon;

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