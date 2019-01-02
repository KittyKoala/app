package com.kangyonggan.app.controller.dashboard.content;

import com.kangyonggan.app.annotation.PermissionMenu;
import com.kangyonggan.app.annotation.Token;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Dict;
import com.kangyonggan.app.service.DictService;
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
@RequestMapping("dashboard/content/dict")
public class DashboardContentDictController extends BaseController {

    private static final String PATH_ROOT = "dashboard/content/dict";

    @Autowired
    private DictService dictService;

    /**
     * 字典列表界面
     *
     * @return
     */
    @GetMapping
    @PermissionMenu("CONTENT_DICT")
    public String index() {
        return PATH_ROOT + "/list";
    }

    /**
     * 字典列表查询
     *
     * @return
     */
    @GetMapping("list")
    @PermissionMenu("CONTENT_DICT")
    @ResponseBody
    public Page<Dict> list() {
        return new Page<>(dictService.searchDicts(getRequestParams()));
    }

    /**
     * 添加字典
     *
     * @param model
     * @return
     */
    @GetMapping("create")
    @PermissionMenu("CONTENT_DICT")
    @Token(key = "createDict")
    public String create(Model model) {
        model.addAttribute("dict", new Dict());
        return PATH_ROOT + "/form-modal";
    }

    /**
     * 保存字典
     *
     * @param dict
     * @return
     */
    @PostMapping("save")
    @ResponseBody
    @PermissionMenu("CONTENT_DICT")
    @Token(key = "createDict", type = Token.Type.CHECK)
    public Response save(Dict dict) {
        dictService.saveDict(dict);
        return Response.getSuccessResponse();
    }

    /**
     * 编辑字典
     *
     * @param dictId
     * @param model
     * @return
     */
    @GetMapping("{dictId:[\\d]+}/edit")
    @PermissionMenu("CONTENT_DICT")
    @Token(key = "editDict")
    public String edit(@PathVariable("dictId") Long dictId, Model model) {
        model.addAttribute("dict", dictService.findDictByDictId(dictId));
        return PATH_ROOT + "/form-modal";
    }

    /**
     * 更新字典
     *
     * @param dict
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    @PermissionMenu("CONTENT_DICT")
    @Token(key = "editDict", type = Token.Type.CHECK)
    public Response update(Dict dict) {
        dictService.updateDict(dict);
        return Response.getSuccessResponse();
    }

    /**
     * 批量删除字典
     *
     * @param dictIds 字典ID
     * @return 响应
     */
    @GetMapping("delete")
    @PermissionMenu("CONTENT_DICT")
    @ResponseBody
    public Response delete(@RequestParam("dictIds") String dictIds) {
        dictService.deleteDicts(dictIds);
        return Response.getSuccessResponse();
    }

    /**
     * 批量恢复字典
     *
     * @param dictIds 字典ID
     * @return 响应
     */
    @GetMapping("restore")
    @PermissionMenu("CONTENT_DICT")
    @ResponseBody
    public Response restore(@RequestParam("dictIds") String dictIds) {
        dictService.restoreDicts(dictIds);
        return Response.getSuccessResponse();
    }

}
