package com.kangyonggan.app.controller.dashboard.system;

import com.kangyonggan.app.annotation.PermissionMenu;
import com.kangyonggan.app.annotation.Token;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.dto.Page;
import com.kangyonggan.app.dto.UserDto;
import com.kangyonggan.app.service.UserService;
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
@RequestMapping("dashboard/system/user")
public class DashboardSystemUserController extends BaseController {

    private static final String PATH_ROOT = "dashboard/system/user";

    @Autowired
    private UserService userService;

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
     * 批量删除用户
     *
     * @param userIds 用户ID
     * @return 响应
     */
    @GetMapping("delete")
    @PermissionMenu("SYSTEM_USER")
    @ResponseBody
    public Response delete(@RequestParam("userIds") String userIds) {
        userService.deleteUsers(userIds);
        return Response.getSuccessResponse();
    }

    /**
     * 批量恢复用户
     *
     * @param userIds 用户ID
     * @return 响应
     */
    @GetMapping("restore")
    @PermissionMenu("SYSTEM_USER")
    @ResponseBody
    public Response restore(@RequestParam("userIds") String userIds) {
        userService.restoreUsers(userIds);
        return Response.getSuccessResponse();
    }
}
