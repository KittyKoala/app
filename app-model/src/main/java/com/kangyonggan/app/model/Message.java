package com.kangyonggan.app.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Table(name = "tb_message")
@Data
public class Message implements Serializable {
    /**
     * 消息ID
     */
    @Id
    @Column(name = "message_id")
    private Long messageId;

    /**
     * 栏目代码
     */
    @Column(name = "category_code")
    private String categoryCode;

    /**
     * 标题
     */
    private String title;

    /**
     * 发送者
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 逻辑删除:{0:未删除, 1:已删除}
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
     * 消息内容
     */
    private String content;

    private static final long serialVersionUID = 1L;
}