package com.kangyonggan.app.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.github.ofofs.jca.annotation.Serial;
import lombok.Data;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Table(name = "tb_section")
@Data
@Serial
public class Section implements Serializable {
    /**
     * 章节ID
     */
    @Id
    @Column(name = "section_id")
    private Long sectionId;

    /**
     * 小说代码
     */
    @Column(name = "novel_code")
    private String novelCode;

    /**
     * 章节代码
     */
    private String code;

    /**
     * 标题
     */
    private String title;

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
     * 内容
     */
    private String content;

    private static final long serialVersionUID = 1L;
}