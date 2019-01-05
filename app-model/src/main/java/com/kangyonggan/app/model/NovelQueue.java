package com.kangyonggan.app.model;

import com.github.ofofs.jca.annotation.Serial;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Table(name = "tb_novel_queue")
@Data
@Serial
public class NovelQueue implements Serializable {
    /**
     * 队列ID
     */
    @Id
    @Column(name = "queue_id")
    private Long queueId;

    /**
     * 小说ID
     */
    @Column(name = "novel_id")
    private Long novelId;

    /**
     * 状态
     */
    private String status;

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