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
@Table(name = "tb_album_photo")
@Data
@Serial
public class AlbumPhoto implements Serializable {
    /**
     * 照片ID
     */
    @Id
    @Column(name = "photo_id")
    private Long photoId;

    /**
     * 相册ID
     */
    @Column(name = "album_id")
    private Long albumId;

    /**
     * 描述
     */
    private String description;

    /**
     * 图片地址
     */
    private String url;

    /**
     * 排序(从0开始)
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

    private static final long serialVersionUID = 1L;
}