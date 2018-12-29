package com.kangyonggan.app.controller.dashboard.system;

import com.kangyonggan.app.annotation.PermissionMenu;
import com.kangyonggan.app.annotation.Token;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Menu;
import com.kangyonggan.app.service.MenuService;
import com.kangyonggan.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kangyonggan
 * @since 8/10/18
 */
@Controller
@RequestMapping("dashboard/system/menu")
public class DashboardSystemMenuController extends BaseController {

    private static final String PATH_ROOT = "dashboard/system/menu";

    @Autowired
    private MenuService menuService;

    /**
     * 菜单管理界面
     *
     * @param model
     * @return
     */
    @GetMapping
    @PermissionMenu("SYSTEM_MENU")
    public String index(Model model) {
        List<Menu> allMenus = menuService.findAllMenus();

        model.addAttribute("allMenus", allMenus);
        return PATH_ROOT + "/index";
    }

    /**
     * 添加菜单
     *
     * @param parentCode
     * @param model
     * @return
     */
    @GetMapping("create")
    @PermissionMenu("SYSTEM_MENU")
    @Token(key = "createMenu")
    public String create(@RequestParam(value = "parentCode", defaultValue = "") String parentCode,
                         Model model) {
        model.addAttribute("menu", new Menu());
        model.addAttribute("parentMenu", menuService.findMenuByCode(parentCode));
        return PATH_ROOT + "/form-modal";
    }

    /**
     * 保存菜单
     *
     * @param menu
     * @return
     */
    @PostMapping("save")
    @PermissionMenu("SYSTEM_MENU")
    @ResponseBody
    @Token(key = "createMenu", type = Token.Type.CHECK)
    public Response save(Menu menu) {
        menuService.saveMenu(menu);
        return Response.getSuccessResponse();
    }

    /**
     * 编辑菜单
     *
     * @param menuId
     * @param model
     * @return
     */
    @GetMapping("{menuId:[\\d]+}/edit")
    @PermissionMenu("SYSTEM_MENU")
    @Token(key = "editMenu")
    public String edit(@PathVariable("menuId") Long menuId, Model model) {
        Menu menu = menuService.findMenuByMenuId(menuId);

        model.addAttribute("menu", menu);
        model.addAttribute("parentMenu", menuService.findMenuByCode(menu.getParentCode()));
        return PATH_ROOT + "/form-modal";
    }

    /**
     * 更新菜单
     *
     * @param menu
     * @return
     */
    @PostMapping("update")
    @PermissionMenu("SYSTEM_MENU")
    @ResponseBody
    @Token(key = "editMenu", type = Token.Type.CHECK)
    public Response update(Menu menu) {
        menuService.updateMenu(menu);
        return Response.getSuccessResponse();
    }

    /**
     * 删除菜单
     *
     * @param menu
     * @return
     */
    @GetMapping("delete")
    @PermissionMenu("SYSTEM_MENU")
    @ResponseBody
    public Response delete(Menu menu) {
        menuService.deleteMenu(menu);
        return Response.getSuccessResponse();
    }
}
