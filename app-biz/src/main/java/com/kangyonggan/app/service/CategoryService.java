package com.kangyonggan.app.service;

import com.kangyonggan.app.model.Category;
import com.kangyonggan.common.Params;

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

    /**
     * 根据类型查找子栏目
     *
     * @param type
     * @return
     */
    List<Category> findCategoriesByType(String type);

    /**
     * 搜索栏目
     *
     * @param params
     * @return
     */
    List<Category> searchCategories(Params params);

    /**
     * 保存栏目
     *
     * @param category
     */
    void saveCategory(Category category);

    /**
     * 查找栏目
     *
     * @param categoryId
     * @return
     */
    Category findCategoryByCategoryId(Long categoryId);

    /**
     * 更新栏目
     *
     * @param category
     */
    void updateCategory(Category category);

    /**
     * 批量删除栏目
     *
     * @param categoryIds
     */
    void deleteCategories(String categoryIds);

    /**
     * 批量恢复栏目
     *
     * @param categoryIds
     */
    void restoreCategories(String categoryIds);

    /**
     * 判断栏目是否存在
     *
     * @param categoryType
     * @param categoryCode
     * @return
     */
    boolean existsCategory(String categoryType, String categoryCode);

    /**
     * 查找栏目
     *
     * @param type
     * @param code
     * @return
     */
    Category findCategoryByTypeAndCode(String type, String code);
}
