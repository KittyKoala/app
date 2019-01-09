package com.kangyonggan.app.controller.api;

import com.kangyonggan.app.service.*;
import com.kangyonggan.app.util.IdNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kangyonggan
 * @since 8/9/18
 */
@Controller
@RequestMapping("api/validate")
public class ApiValidateController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private DictService dictService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private NovelService novelService;

    /**
     * 校验电子邮箱是否可用
     *
     * @param email
     * @return
     */
    @GetMapping("email")
    @ResponseBody
    public boolean email(@RequestParam("email") String email) {
        return !userService.existsEmail(email);
    }

    /**
     * 校验身份证是否可用
     *
     * @param idNo
     * @return
     */
    @GetMapping("idNo")
    @ResponseBody
    public boolean idNo(@RequestParam("idNo") String idNo) {
        return IdNoUtil.isIdCard18(idNo);
    }

    /**
     * 校验角色代码是否可用
     *
     * @param roleCode
     * @return
     */
    @GetMapping("roleCode")
    @ResponseBody
    public boolean roleCode(@RequestParam("roleCode") String roleCode) {
        return !roleService.existsRoleCode(roleCode);
    }

    /**
     * 校验菜单代码是否可用
     *
     * @param menuCode
     * @return
     */
    @GetMapping("menuCode")
    @ResponseBody
    public boolean menuCode(@RequestParam("menuCode") String menuCode) {
        return !menuService.existsMenuCode(menuCode);
    }

    /**
     * 校验字典代码是否可用
     *
     * @param oldDictType
     * @param oldDictCode
     * @param dictType
     * @param dictCode
     * @return
     */
    @GetMapping("dictCode")
    @ResponseBody
    public boolean dictCode(@RequestParam(value = "oldDictType", required = false, defaultValue = "") String oldDictType,
                            @RequestParam(value = "oldDictCode", required = false, defaultValue = "") String oldDictCode,
                            @RequestParam("dictType") String dictType, @RequestParam("dictCode") String dictCode) {
        if (dictType.equals(oldDictType) && dictCode.equals(oldDictCode)) {
            return true;
        }

        return !dictService.existsDict(dictType, dictCode);
    }

    /**
     * 校验小说代码是否可用
     *
     * @param oldSource
     * @param oldCode
     * @param source
     * @param code
     * @return
     */
    @GetMapping("novelCode")
    @ResponseBody
    public boolean novelCode(@RequestParam(value = "oldSource", required = false, defaultValue = "") String oldSource,
                            @RequestParam(value = "oldCode", required = false, defaultValue = "") String oldCode,
                            @RequestParam("source") String source, @RequestParam("code") String code) {
        if (source.equals(oldSource) && code.equals(oldCode)) {
            return true;
        }

        return !novelService.existsNovel(source, code);
    }

    /**
     * 校验栏目代码是否可用
     *
     * @param oldCategoryType
     * @param oldCategoryCode
     * @param categoryType
     * @param categoryCode
     * @return
     */
    @GetMapping("categoryCode")
    @ResponseBody
    public boolean categoryCode(@RequestParam(value = "oldCategoryType", required = false, defaultValue = "") String oldCategoryType,
                            @RequestParam(value = "oldCategoryCode", required = false, defaultValue = "") String oldCategoryCode,
                            @RequestParam("categoryType") String categoryType, @RequestParam("categoryCode") String categoryCode) {
        if (categoryType.equals(oldCategoryType) && categoryCode.equals(oldCategoryCode)) {
            return true;
        }

        return !categoryService.existsCategory(categoryType, categoryCode);
    }

}
