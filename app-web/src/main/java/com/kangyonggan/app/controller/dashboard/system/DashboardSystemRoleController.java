package com.kangyonggan.app.controller.dashboard.system;

import com.kangyonggan.app.annotation.PermissionMenu;
import com.kangyonggan.app.annotation.Token;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Role;
import com.kangyonggan.app.service.MenuService;
import com.kangyonggan.app.service.RoleService;
import com.kangyonggan.app.service.UserService;
import com.kangyonggan.common.Page;
import com.kangyonggan.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author kangyonggan
 * @since 12/29/18
 */
@Controller
@RequestMapping("dashboard/system/role")
public class DashboardSystemRoleController extends BaseController {

    private static final String PATH_ROOT = "dashboard/system/role";

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    /**
     * 角色列表界面
     *
     * @return
     */
    @GetMapping
    @PermissionMenu("SYSTEM_ROLE")
    public String index() {
        return PATH_ROOT + "/list";
    }

    /**
     * 角色列表查询
     *
     * @return
     */
    @GetMapping("list")
    @PermissionMenu("SYSTEM_ROLE")
    @ResponseBody
    public Page<Role> list() {
        return new Page<>(roleService.searchRoles(getRequestParams()));
    }


    /**
     * 添加角色
     *
     * @param model
     * @return
     */
    @GetMapping("create")
    @PermissionMenu("SYSTEM_ROLE")
    @Token(key = "createRole")
    public String create(Model model) {
        model.addAttribute("role", new Role());
        return PATH_ROOT + "/form-modal";
    }

    /**
     * 保存角色
     *
     * @param role
     * @return
     */
    @PostMapping("save")
    @ResponseBody
    @PermissionMenu("SYSTEM_ROLE")
    @Token(key = "createRole", type = Token.Type.CHECK)
    public Response save(Role role) {
        roleService.saveRole(role);
        return Response.getSuccessResponse();
    }

    /**
     * 编辑角色
     *
     * @param roleId
     * @param model
     * @return
     */
    @GetMapping("{roleId:[\\d]+}/edit")
    @PermissionMenu("SYSTEM_ROLE")
    @Token(key = "editRole")
    public String edit(@PathVariable("roleId") Long roleId, Model model) {
        model.addAttribute("role", roleService.findRoleByRoleId(roleId));
        return PATH_ROOT + "/form-modal";
    }

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    @PermissionMenu("SYSTEM_ROLE")
    @Token(key = "editRole", type = Token.Type.CHECK)
    public Response update(Role role) {
        roleService.updateRole(role);
        return Response.getSuccessResponse();
    }

    /**
     * 批量删除角色
     *
     * @param roleIds 角色ID
     * @return 响应
     */
    @GetMapping("delete")
    @PermissionMenu("SYSTEM_ROLE")
    @ResponseBody
    public Response delete(@RequestParam("roleIds") String roleIds) {
        roleService.deleteRoles(roleIds);
        return Response.getSuccessResponse();
    }

    /**
     * 批量恢复角色
     *
     * @param roleIds 角色ID
     * @return 响应
     */
    @GetMapping("restore")
    @PermissionMenu("SYSTEM_ROLE")
    @ResponseBody
    public Response restore(@RequestParam("roleIds") String roleIds) {
        roleService.restoreRoles(roleIds);
        return Response.getSuccessResponse();
    }
}
