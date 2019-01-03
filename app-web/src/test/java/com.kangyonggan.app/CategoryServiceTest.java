package com.kangyonggan.app;

import com.kangyonggan.app.constants.CategoryType;
import com.kangyonggan.app.model.Category;
import com.kangyonggan.app.service.CategoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author kangyonggan
 * @since 1/3/19
 */
public class CategoryServiceTest extends AbstractTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testFindTreeCategoriesByType() {
        List<Category> categories = categoryService.findTreeCategoriesByType(CategoryType.NAV_BAR.getCode());
        System.out.println(categories);
    }

}
