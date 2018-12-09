package com.kangyonggan.app.controller.dashboard.system;

import com.kangyonggan.app.annotation.PermissionMenu;
import com.kangyonggan.app.annotation.Token;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.dto.Page;
import com.kangyonggan.app.dto.UserDto;
import com.kangyonggan.app.model.Role;
import com.kangyonggan.app.model.User;
import com.kangyonggan.app.service.RoleService;
import com.kangyonggan.app.service.UserService;
import com.kangyonggan.app.util.Collections3;
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
@RequestMapping("dashboard/system/user")
public class DashboardSystemUserController extends BaseController {

    private static final String PATH_ROOT = "dashboard/system/user";

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 用户列表界面
     *
     * @return
     */
    @GetMapping
    @PermissionMenu("SYSTEM_USER")
    public String index() {
        return PATH_ROOT + "/list";
    }

    /**
     * 用户列表查询
     *
     * @return
     */
    @GetMapping("list")
    @PermissionMenu("SYSTEM_USER")
    @ResponseBody
    public Page<UserDto> list() {
        return new Page<>(userService.searchUsers(getRequestParams()));
    }

    /**
     * 删除/恢复
     *
     * @param userId    用户ID
     * @param isDeleted 是否删除
     * @return 响应
     */
    @GetMapping("{userId:[\\d]+}/delete/{isDeleted:\\b0\\b|\\b1\\b}")
    @PermissionMenu("SYSTEM_USER")
    @ResponseBody
    public Response delete(@PathVariable("userId") Long userId, @PathVariable("isDeleted") byte isDeleted) {
        User user = userService.findUserByUserId(userId);
        user.setIsDeleted(isDeleted);
        userService.updateUser(user);
        return Response.getSuccessResponse();
    }

    /**
     * 添加用户
     *
     * @param model
     * @return
     */
    @GetMapping("create")
    @PermissionMenu("SYSTEM_USER")
    @Token(key = "createUser")
    public String create(Model model) {
        model.addAttribute("userDto", new UserDto());
        return PATH_ROOT + "/form-modal";
    }

    /**
     * 保存用户
     *
     * @param userDto
     * @return
     */
    @PostMapping("save")
    @ResponseBody
    @PermissionMenu("SYSTEM_USER")
    @Token(key = "createUser", type = Token.Type.CHECK)
    public Response save(UserDto userDto) {
        userService.saveUser(userDto);
        return Response.getSuccessResponse();
    }

    /**
     * 编辑用户
     *
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("{userId:[\\d]+}/edit")
    @PermissionMenu("SYSTEM_USER")
    @Token(key = "editUser")
    public String edit(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("userDto", userService.findUserDtoByUserId(userId));
        return PATH_ROOT + "/form-modal";
    }

    /**
     * 更新用户
     *
     * @param userDto
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    @PermissionMenu("SYSTEM_USER")
    @Token(key = "editUser", type = Token.Type.CHECK)
    public Response update(UserDto userDto) {
        userService.updateUser(userDto);
        return Response.getSuccessResponse();
    }

    /**
     * 物理删除
     *
     * @param userId
     * @return
     */
    @GetMapping("{userId:[\\d]+}/remove")
    @PermissionMenu("SYSTEM_USER")
    @ResponseBody
    public Response remove(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return Response.getSuccessResponse();
    }

    /**
     * 修改密码
     *
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("{userId:[\\d]+}/password")
    @PermissionMenu("SYSTEM_USER")
    @Token(key = "editPassword")
    public String password(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("user", userService.findUserByUserId(userId));
        return PATH_ROOT + "/password-modal";
    }

    /**
     * 修改密码提交
     *
     * @param user
     * @return
     */
    @PostMapping("password")
    @ResponseBody
    @PermissionMenu("SYSTEM_USER")
    @Token(key = "editPassword", type = Token.Type.CHECK)
    public Response password(User user) {
        userService.updateUserPassword(user);
        return Response.getSuccessResponse();
    }

    /**
     * 设置角色
     *
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("{userId:[\\d]+}/roles")
    @PermissionMenu("SYSTEM_USER")
    @Token(key = "setRoles")
    public String roles(@PathVariable("userId") Long userId, Model model) {
        List<Role> userRoles = roleService.findRolesByUserId(userId);
        userRoles = Collections3.extractToList(userRoles, "roleId");
        List<Role> allRoles = roleService.findAllRoles();

        model.addAttribute("userId", userId);
        model.addAttribute("userRoles", userRoles);
        model.addAttribute("allRoles", allRoles);
        return PATH_ROOT + "/roles-modal";
    }

    /**
     * 保存角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    @PostMapping("{userId:[\\d]+}/roles")
    @PermissionMenu("SYSTEM_USER")
    @ResponseBody
    @Token(key = "setRoles", type = Token.Type.CHECK)
    public Response updateUserRoles(@PathVariable(value = "userId") Long userId,
                                    @RequestParam(value = "roleIds", defaultValue = "") String roleIds) {
        userService.updateUserRoles(userId, roleIds);

        return Response.getSuccessResponse();
    }
}
