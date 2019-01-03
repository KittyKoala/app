package com.kangyonggan.app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Table(name = "tb_category")
@Data
public class Category implements Serializable {
    /**
     * 栏目ID
     */
    @Id
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 栏目类型
     */
    @Column(name = "category_type")
    private String categoryType;

    /**
     * 父栏目代码
     */
    @Column(name = "parent_code")
    private String parentCode;

    /**
     * 栏目代码
     */
    @Column(name = "category_code")
    private String categoryCode;

    /**
     * 栏目名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 地址
     */
    private String url;

    /**
     * 栏目排序(从0开始)
     */
    private Integer sort;

    /**
     * 是否开启新界面
     */
    @Column(name = "is_blank")
    private Byte isBlank;

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

    /**
     * 子栏目
     */
    @Transient
    private List<Category> leaf;

    private static final long serialVersionUID = 1L;
}