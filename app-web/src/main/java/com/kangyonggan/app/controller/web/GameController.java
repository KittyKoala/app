package com.kangyonggan.app.controller.web;

import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.common.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * @author kangyonggan
 * @since 1/15/19
 */
@Controller
@RequestMapping("game")
@Log4j2
public class GameController extends BaseController {

    /**
     * 猜数字
     *
     * @return
     */
    @GetMapping("guessNum")
    public String guessNum() {
        return "web/game/guessNum";
    }

    /**
     * 生成4位不重复的随机数字
     *
     * @return
     */
    @GetMapping("genNum")
    @ResponseBody
    public Response genNum() {
        Response response = Response.getSuccessResponse();
        int len = 4;
        Random ran = new Random();
        int bitField = 0;
        char[] chs = new char[len];
        for (int i = 0; i < len; i++) {
            while (true) {
                int k = ran.nextInt(10);
                if ((bitField & (1 << k)) == 0) {
                    bitField |= 1 << k;
                    chs[i] = (char) (k + '0');
                    break;
                }
            }
        }

        response.put("num", new String(chs));
        log.info("生成4位不重复的随机数字：{}", response.get("num"));
        return response;
    }

}
