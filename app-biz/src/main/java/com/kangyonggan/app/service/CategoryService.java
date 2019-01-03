package com.kangyonggan.app.service;

import com.kangyonggan.app.model.Category;

import java.util.List;

/**
 * @author kangyonggan
 * @since 12/6/18
 */
public interface CategoryService {

    /**
     * 根据类型查找树状栏目
     *
     * @param type
     * @return
     */
    List<Category> findTreeCategoriesByType(String type);
}
