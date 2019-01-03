package com.kangyonggan.app.service.impl;

import com.github.ofofs.jca.annotation.Cache;
import com.kangyonggan.app.constants.YesNo;
import com.kangyonggan.app.model.Category;
import com.kangyonggan.app.service.CategoryService;
import com.kangyonggan.common.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangyonggan
 * @since 1/3/19
 */
@Service
public class CategoryServiceImpl extends BaseService<Category> implements CategoryService {

    @Override
//    @Cache("category:type:${type}")
    public List<Category> findTreeCategoriesByType(String type) {
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("categoryType", type).andEqualTo("isDeleted", YesNo.NO.getCode());
        example.setOrderByClause("sort asc");
        List<Category> categories = myMapper.selectByExample(example);

        List<Category> wrapList = new ArrayList<>();
        return recursionLeafList(categories, wrapList, StringUtils.EMPTY);
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
