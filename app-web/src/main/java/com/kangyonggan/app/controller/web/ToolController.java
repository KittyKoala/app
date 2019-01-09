package com.kangyonggan.app.controller.web;

import com.kangyonggan.app.constants.IdNoConstants;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.util.DestinyUtil;
import com.kangyonggan.app.util.IdNoUtil;
import com.kangyonggan.common.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author kangyonggan
 * @since 1/8/19
 */
@Controller
@RequestMapping("tool")
public class ToolController extends BaseController {

    private static final String PATH_ROOT = "web/tool";

    /**
     * 工具
     *
     * @return
     */
    @GetMapping
    public String index() {
        return PATH_ROOT + "/index";
    }

    /**
     * 身份证校验界面
     *
     * @return
     */
    @GetMapping("idNoCheck")
    public String idNoCheck() {
        return PATH_ROOT + "/idNoCheck";
    }

    /**
     * 身份证校验界面
     *
     * @param idNo
     * @return
     */
    @PostMapping("idNoCheck")
    @ResponseBody
    public Response idNoCheck(@RequestParam("idNo") String idNo) {
        Response response = Response.getSuccessResponse();
        idNo = idNo.replaceAll("x", "X");

        boolean isIdNo = IdNoUtil.isIdCard18(idNo);
        if (!isIdNo) {
            isIdNo = IdNoUtil.isIdCard15(idNo);
            if (isIdNo) {
                idNo = IdNoUtil.convert15To18(idNo);
            }
        }
        response.put("isIdNo", isIdNo);

        if (isIdNo) {
            try {
                // 原户籍地
                response.put("address", IdNoConstants.getArea(idNo.substring(0, 6)));
                // 出生年月
                response.put("birthday", IdNoUtil.getYearFromIdCard(idNo) + "年"
                        + IdNoUtil.getMonthFromIdCard(idNo) + "月" + IdNoUtil.getDayFromIdCard(idNo) + "日");
                // 生肖
                response.put("shengXiao", DestinyUtil.getShengXiao(Integer.parseInt(IdNoUtil.getYearFromIdCard(idNo))));
                // 星座
                response.put("xingZuo", DestinyUtil.getXingZuo(Integer.parseInt(IdNoUtil.getMonthFromIdCard(idNo)), Integer.parseInt(IdNoUtil.getDayFromIdCard(idNo))));
                // 性别
                response.put("gender", IdNoUtil.getSexFromIdCard(idNo));
            } catch (Exception e) {
                response.put("isIdNo", false);
            }
        }
        return response;
    }

}
