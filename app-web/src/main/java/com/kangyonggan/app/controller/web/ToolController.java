package com.kangyonggan.app.controller.web;

import com.kangyonggan.app.constants.DictType;
import com.kangyonggan.app.constants.IdNoConstants;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Dict;
import com.kangyonggan.app.service.DictService;
import com.kangyonggan.app.util.DestinyUtil;
import com.kangyonggan.app.util.FileHelper;
import com.kangyonggan.app.util.IdNoUtil;
import com.kangyonggan.app.util.SealUtil;
import com.kangyonggan.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangyonggan
 * @since 1/8/19
 */
@Controller
@RequestMapping("tool")
public class ToolController extends BaseController {

    private static final String PATH_ROOT = "web/tool";

    @Autowired
    private FileHelper fileHelper;

    @Autowired
    private DictService dictService;

    /**
     * 身份证查询界面
     *
     * @return
     */
    @GetMapping("idNoCheck")
    public String idNoCheck() {
        return PATH_ROOT + "/idNoCheck";
    }

    /**
     * 身份证查询
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

    /**
     * 生成身份证界面
     *
     * @param model
     * @return
     */
    @GetMapping("idNoGen")
    public String idNoGen(Model model) {
        model.addAttribute("cities", IdNoUtil.getCityCodes());
        return PATH_ROOT + "/idNoGen";
    }

    /**
     * 生成身份证
     *
     * @param prov
     * @param startAge
     * @param endAge
     * @param sex
     * @param len
     * @param size
     * @return
     */
    @PostMapping("idNoGen")
    @ResponseBody
    public Response idNoGen(@RequestParam(value = "prov", required = false, defaultValue = "") String prov,
                            @RequestParam(value = "startAge", required = false, defaultValue = "") Integer startAge,
                            @RequestParam(value = "endAge", required = false, defaultValue = "60") Integer endAge,
                            @RequestParam(value = "sex", required = false, defaultValue = "") String sex,
                            @RequestParam(value = "len", required = false, defaultValue = "-1") Integer len,
                            @RequestParam(value = "size", required = false, defaultValue = "30") Integer size) {
        Response response = Response.getSuccessResponse();
        List<String> idNos = IdNoUtil.genIdCard(prov, startAge, endAge, sex, len, size);

        response.put("idNos", idNos);
        return response;
    }

    /**
     * 电子印章
     *
     * @return
     */
    @GetMapping("seal")
    public String seal() {
        return "web/tool/seal";
    }

    /**
     * 生成电子印章
     *
     * @param name
     * @return
     * @throws Exception
     */
    @PostMapping("seal")
    @ResponseBody
    public Response seal(@RequestParam("name") String name) throws Exception {
        Response response = Response.getSuccessResponse();
        List<Dict> dicts = dictService.findDictsByDictType(DictType.FONT.getCode());
        List<String[]> result = new ArrayList<>();
        for (Dict dict : dicts) {
            String[] arr = new String[2];
            String fileName = fileHelper.genFileName("seal") + ".png";
            SealUtil.build(name, dict.getValue(), fileHelper.getFileUploadPath() + "seal/" + fileName);
            arr[0] = "upload/seal/" + fileName;
            arr[1] = dict.getValue();
            result.add(arr);
        }

        response.put("result", result);
        return response;
    }

    /**
     * 生成GIF界面
     *
     * @return
     */
    @GetMapping("gif")
    public String gif() {
        return PATH_ROOT + "/gif";
    }

}
