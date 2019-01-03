package com.kangyonggan.app.controller.dashboard.content;

import com.kangyonggan.app.annotation.PermissionMenu;
import com.kangyonggan.app.annotation.Token;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Category;
import com.kangyonggan.app.service.CategoryService;
import com.kangyonggan.common.Page;
import com.kangyonggan.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author kangyonggan
 * @since 8/10/18
 */
@Controller
@RequestMapping("dashboard/content/category")
public class DashboardContentCategoryController extends BaseController {

    private static final String PATH_ROOT = "dashboard/content/category";

    @Autowired
    private CategoryService categoryService;

    /**
     * 栏目列表界面
     *
     * @return
     */
    @GetMapping
    @PermissionMenu("CONTENT_CATEGORY")
    public String index() {
        return PATH_ROOT + "/list";
    }

    /**
     * 栏目列表查询
     *
     * @return
     */
    @GetMapping("list")
    @PermissionMenu("CONTENT_CATEGORY")
    @ResponseBody
    public Page<Category> list() {
        return new Page<>(categoryService.searchCategories(getRequestParams()));
    }

    /**
     * 添加栏目
     *
     * @param parentType
     * @param parentCode
     * @param model
     * @return
     */
    @GetMapping("create")
    @PermissionMenu("CONTENT_CATEGORY")
    @Token(key = "createCategory")
    public String create(@RequestParam(value = "parentType", required = false, defaultValue = "") String parentType,
                         @RequestParam(value = "parentCode", required = false, defaultValue = "") String parentCode,
                         Model model) {
        model.addAttribute("parentCategory", categoryService.findCategoryByTypeAndCode(parentType, parentCode));
        model.addAttribute("category", new Category());
        return PATH_ROOT + "/form-modal";
    }

    /**
     * 保存栏目
     *
     * @param category
     * @return
     */
    @PostMapping("save")
    @ResponseBody
    @PermissionMenu("CONTENT_CATEGORY")
    @Token(key = "createCategory", type = Token.Type.CHECK)
    public Response save(Category category) {
        categoryService.saveCategory(category);
        return Response.getSuccessResponse();
    }

    /**
     * 编辑栏目
     *
     * @param categoryId
     * @param model
     * @return
     */
    @GetMapping("{categoryId:[\\d]+}/edit")
    @PermissionMenu("CONTENT_CATEGORY")
    @Token(key = "editDict")
    public String edit(@PathVariable("categoryId") Long categoryId, Model model) {
        Category category = categoryService.findCategoryByCategoryId(categoryId);
        Category parentCategory = categoryService.findCategoryByTypeAndCode(category.getCategoryType(), category.getCategoryCode());

        model.addAttribute("category", category);
        model.addAttribute("parentCategory", parentCategory);
        return PATH_ROOT + "/form-modal";
    }

    /**
     * 更新栏目
     *
     * @param category
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    @PermissionMenu("CONTENT_CATEGORY")
    @Token(key = "editDict", type = Token.Type.CHECK)
    public Response update(Category category) {
        categoryService.updateCategory(category);
        return Response.getSuccessResponse();
    }

    /**
     * 批量删除栏目
     *
     * @param categoryIds 栏目ID
     * @return 响应
     */
    @GetMapping("delete")
    @PermissionMenu("CONTENT_CATEGORY")
    @ResponseBody
    public Response delete(@RequestParam("categoryIds") String categoryIds) {
        categoryService.deleteCategories(categoryIds);
        return Response.getSuccessResponse();
    }

    /**
     * 批量恢复栏目
     *
     * @param categoryIds 栏目ID
     * @return 响应
     */
    @GetMapping("restore")
    @PermissionMenu("CONTENT_CATEGORY")
    @ResponseBody
    public Response restore(@RequestParam("categoryIds") String categoryIds) {
        categoryService.restoreCategories(categoryIds);
        return Response.getSuccessResponse();
    }
}
