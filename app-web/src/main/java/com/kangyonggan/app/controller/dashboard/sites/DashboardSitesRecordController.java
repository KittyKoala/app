package com.kangyonggan.app.controller.dashboard.sites;

import com.kangyonggan.app.annotation.PermissionMenu;
import com.kangyonggan.app.annotation.Token;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Record;
import com.kangyonggan.app.service.RecordService;
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
@RequestMapping("dashboard/sites/record")
public class DashboardSitesRecordController extends BaseController {

    private static final String PATH_ROOT = "dashboard/sites/record";

    @Autowired
    private RecordService recordService;

    /**
     * 宝宝点滴列表界面
     *
     * @return
     */
    @GetMapping
    @PermissionMenu("SITES_NOVEL_QUEUE")
    public String index() {
        return PATH_ROOT + "/list";
    }

    /**
     * 宝宝点滴列表查询
     *
     * @return
     */
    @GetMapping("list")
    @PermissionMenu("SITES_NOVEL_QUEUE")
    @ResponseBody
    public Page<Record> list() {
        return new Page<>(recordService.searchRecords(getRequestParams()));
    }

    /**
     * 编辑宝宝点滴
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("{id:[\\d]+}/edit")
    @PermissionMenu("SITES_NOVEL_QUEUE")
    @Token(key = "editRecord")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("record", recordService.findRecordById(id));
        return PATH_ROOT + "/form-modal";
    }

    /**
     * 更新宝宝点滴
     *
     * @param record
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    @PermissionMenu("SITES_NOVEL_QUEUE")
    @Token(key = "editRecord", type = Token.Type.CHECK)
    public Response update(Record record) {
        recordService.updateRecord(record);
        return Response.getSuccessResponse();
    }

    /**
     * 批量删除宝宝点滴
     *
     * @param ids 宝宝点滴ID
     * @return 响应
     */
    @GetMapping("delete")
    @PermissionMenu("SITES_NOVEL_QUEUE")
    @ResponseBody
    public Response delete(@RequestParam("ids") String ids) {
        recordService.deleteRecords(ids);
        return Response.getSuccessResponse();
    }

    /**
     * 批量恢复宝宝点滴
     *
     * @param ids 宝宝点滴ID
     * @return 响应
     */
    @GetMapping("restore")
    @PermissionMenu("SITES_NOVEL_QUEUE")
    @ResponseBody
    public Response restore(@RequestParam("ids") String ids) {
        recordService.restoreRecords(ids);
        return Response.getSuccessResponse();
    }
}
