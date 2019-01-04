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
@Table(name = "tb_novel")
@Data
@Serial
public class Novel implements Serializable {
    /**
     * 书籍ID
     */
    @Id
    @Column(name = "novel_id")
    private Long novelId;

    /**
     * 来源
     */
    private String source;

    /**
     * 书籍代码
     */
    private String code;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 封面
     */
    private String cover;

    /**
     * 描述
     */
    private String summary;

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