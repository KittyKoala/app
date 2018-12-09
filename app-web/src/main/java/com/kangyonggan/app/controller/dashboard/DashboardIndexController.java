package com.kangyonggan.app.controller.dashboard;

import com.kangyonggan.app.annotation.PermissionMenu;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.User;
import com.kangyonggan.app.model.UserProfile;
import com.kangyonggan.app.service.UserProfileService;
import com.kangyonggan.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author kangyonggan
 * @since 2018/12/9 0009
 */
@Controller
@RequestMapping("dashboard")
public class DashboardIndexController extends BaseController {

    @Autowired
    private UserProfileService userProfileService;

    /**
     * 工作台
     *
     * @return
     */
    @GetMapping
    @PermissionMenu("DASHBOARD")
    public String index() {
        return "dashboard/index";
    }

    /**
     * 修改皮肤
     *
     * @param skin
     * @return
     */
    @PostMapping("changeSkin")
    @ResponseBody
    @PermissionMenu("DASHBOARD")
    public Response changeSkin(@RequestParam("skin") String skin) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(currentUserId());
        userProfile.setSkin(skin);
        userProfileService.updateUserProfile(userProfile);
        return Response.getSuccessResponse();
    }

}
