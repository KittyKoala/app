package com.kangyonggan.app.controller.dashboard.content;

import com.kangyonggan.app.annotation.PermissionMenu;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.service.SqlService;
import com.kangyonggan.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author kangyonggan
 * @since 12/29/18
 */
@Controller
@RequestMapping("dashboard/content/sql")
public class DashboardContentSqlController extends BaseController {

    private static final String PATH_ROOT = "dashboard/content/sql";

    @Autowired
    private SqlService sqlService;

    /**
     * SQL执行界面
     *
     * @return
     */
    @GetMapping
    @PermissionMenu("CONTENT_SQL")
    public String index() {
        return PATH_ROOT + "/index";
    }

    /**
     * SQL执行
     *
     * @param sql
     * @return
     * @throws Exception
     */
    @PostMapping
    @PermissionMenu("CONTENT_SQL")
    @ResponseBody
    public Response execute(@RequestParam("sql") String sql) throws Exception {
        Response response = Response.getSuccessResponse();
        int rows = sqlService.execute(sql);

        response.put("rows", rows);
        return response;
    }

}
