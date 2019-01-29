package com.kangyonggan.app.service.impl;

import com.github.ofofs.jca.annotation.Cache;
import com.github.ofofs.jca.annotation.CacheDel;
import com.github.pagehelper.PageHelper;
import com.kangyonggan.app.constants.YesNo;
import com.kangyonggan.app.model.Category;
import com.kangyonggan.app.service.CategoryService;
import com.kangyonggan.app.util.StringUtil;
import com.kangyonggan.common.BaseService;
import com.kangyonggan.common.Params;
import com.kangyonggan.common.Query;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kangyonggan
 * @since 1/3/19
 */
@Service
public class CategoryServiceImpl extends BaseService<Category> implements CategoryService {

    @Override
    @Cache("category:type:${type}")
    public List<Category> findTreeCategoriesByType(String type) {
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("categoryType", type).andEqualTo("isDeleted", YesNo.NO.getCode());
        example.setOrderByClause("sort asc");
        List<Category> categories = myMapper.selectByExample(example);

        List<Category> wrapList = new ArrayList<>();
        return recursionLeafList(categories, wrapList, StringUtils.EMPTY);
    }

    @Override
    @Cache("category:drop:type:${type}")
    public List<Category> findCategoriesByType(String type) {
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("categoryType", type).andEqualTo("isDeleted", YesNo.NO.getCode())
                .andNotEqualTo("url", StringUtils.EMPTY);
        example.setOrderByClause("category_id asc");
        return myMapper.selectByExample(example);
    }

    @Override
    public List<Category> searchCategories(Params params) {
        Example example = new Example(Category.class);
        Query query = params.getQuery();

        Example.Criteria criteria = example.createCriteria();
        String categoryType = query.getString("categoryType");
        if (StringUtils.isNotEmpty(categoryType)) {
            criteria.andEqualTo("categoryType", categoryType);
        }
        String categoryCode = query.getString("categoryCode");
        if (StringUtils.isNotEmpty(categoryCode)) {
            criteria.andEqualTo("categoryCode", categoryCode);
        }
        String categoryName = query.getString("categoryName");
        if (StringUtils.isNotEmpty(categoryName)) {
            criteria.andEqualTo("categoryName", categoryName);
        }

        String sort = params.getSort();
        String order = params.getOrder();
        if (!StringUtil.hasEmpty(sort, order)) {
            example.setOrderByClause(sort + " " + order.toUpperCase());
        } else {
            example.setOrderByClause("category_id DESC");
        }

        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        return myMapper.selectByExample(example);
    }

    @Override
    @CacheDel("category:*")
    public void saveCategory(Category category) {
        myMapper.insertSelective(category);
    }

    @Override
    public Category findCategoryByCategoryId(Long categoryId) {
        return myMapper.selectByPrimaryKey(categoryId);
    }

    @Override
    @CacheDel("category:*")
    public void updateCategory(Category category) {
        myMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    @CacheDel("category:*")
    public void deleteCategories(String categoryIds) {
        if (StringUtils.isEmpty(categoryIds)) {
            return;
        }
        String[] ids = categoryIds.split(",");
        Example example = new Example(Category.class);
        example.createCriteria().andIn("categoryId", Arrays.asList(ids));

        Category category = new Category();
        category.setIsDeleted(YesNo.YES.getCode());

        myMapper.updateByExampleSelective(category, example);
    }

    @Override
    @CacheDel("category:*")
    public void restoreCategories(String categoryIds) {
        if (StringUtils.isEmpty(categoryIds)) {
            return;
        }
        String[] ids = categoryIds.split(",");
        Example example = new Example(Category.class);
        example.createCriteria().andIn("categoryId", Arrays.asList(ids));

        Category category = new Category();
        category.setIsDeleted(YesNo.NO.getCode());

        myMapper.updateByExampleSelective(category, example);
    }

    @Override
    public boolean existsCategory(String categoryType, String categoryCode) {
        Category category = new Category();
        category.setCategoryType(categoryType);
        category.setCategoryCode(categoryCode);

        return super.exists(category);
    }

    @Override
    public Category findCategoryByTypeAndCode(String type, String code) {
        if (StringUtil.hasEmpty(type, code)) {
            return null;
        }

        Category category = new Category();
        category.setCategoryType(type);
        category.setCategoryCode(code);

        return myMapper.selectOne(category);
    }

    /**
     * 递归构造叶子节点
     *
     * @param from
     * @param toList
     * @param parentCode
     * @return
     */
    private List<Category> recursionLeafList(List<Category> from, List<Category> toList, String parentCode) {
        if (CollectionUtils.isEmpty(from)) {
            return null;
        }

        for (int i = 0; i < from.size(); i++) {
            Category category = from.get(i);
            if (parentCode.equals(category.getParentCode())) {
                List<Category> leaf = new ArrayList<>();
                category.setLeaf(leaf);
                toList.add(category);
                recursionLeafList(from, leaf, category.getCategoryCode());
            }
        }
        return toList;
    }
}
