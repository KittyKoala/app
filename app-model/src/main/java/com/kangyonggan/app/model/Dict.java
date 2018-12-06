package com.kangyonggan.app.model;

import com.github.ofofs.jca.annotation.Serial;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Table(name = "tb_dict")
@Data
@Serial
public class Dict {
    /**
     * 字典ID
     */
    @Id
    @Column(name = "dict_id")
    private Long dictId;

    /**
     * 字典类型
     */
    @Column(name = "dict_type")
    private String dictType;

    /**
     * 字典代码
     */
    @Column(name = "dict_code")
    private String dictCode;

    /**
     * 值
     */
    private String value;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序（从0开始）
     */
    private Integer sort;

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
}