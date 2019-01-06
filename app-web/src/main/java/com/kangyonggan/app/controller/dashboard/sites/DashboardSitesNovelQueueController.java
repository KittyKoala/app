package com.kangyonggan.app.controller.dashboard.sites;

import com.kangyonggan.app.annotation.PermissionMenu;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.NovelQueue;
import com.kangyonggan.app.service.NovelQueueService;
import com.kangyonggan.common.Page;
import com.kangyonggan.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kangyonggan
 * @since 12/29/18
 */
@Controller
@RequestMapping("dashboard/sites/novelQueue")
public class DashboardSitesNovelQueueController extends BaseController {

    private static final String PATH_ROOT = "dashboard/sites/novelQueue";

    @Autowired
    private NovelQueueService novelQueueService;

    /**
     * 小说队列列表界面
     *
     * @return
     */
    @GetMapping
    @PermissionMenu("SITES_NOVEL_QUEUE")
    public String index() {
        return PATH_ROOT + "/list";
    }

    /**
     * 小说队列列表查询
     *
     * @return
     */
    @GetMapping("list")
    @PermissionMenu("SITES_NOVEL_QUEUE")
    @ResponseBody
    public Page<NovelQueue> list() {
        return new Page<>(novelQueueService.searchNovelQueues(getRequestParams()));
    }

    /**
     * 批量完成小说队列
     *
     * @param queueId 队列ID
     * @return 响应
     */
    @GetMapping("finished")
    @PermissionMenu("SITES_NOVEL_QUEUE")
    @ResponseBody
    public Response finished(@RequestParam("queueId") String queueId) {
        novelQueueService.finished(queueId);
        return Response.getSuccessResponse();
    }

}
