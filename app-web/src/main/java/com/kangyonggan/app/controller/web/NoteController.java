package com.kangyonggan.app.controller.web;

import com.kangyonggan.app.constants.AppConstants;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Note;
import com.kangyonggan.app.service.NoteService;
import com.kangyonggan.app.util.RedisSession;
import com.kangyonggan.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author kangyonggan
 * @since 2019-02-01
 */
@Controller
@RequestMapping("note")
public class NoteController extends BaseController {

    @Autowired
    private NoteService noteService;

    /**
     * 留言界面
     *
     * @return
     */
    @GetMapping
    public String index() {
        return "web/note/index";
    }

    /**
     * 留言
     *
     * @param captcha
     * @param note
     * @return
     */
    @PostMapping
    @ResponseBody
    public Response save(@RequestParam("captcha") String captcha, Note note) {
        String realCaptcha = RedisSession.getString(AppConstants.KEY_CAPTCHA);

        // 清除验证码
        RedisSession.delete(AppConstants.KEY_CAPTCHA);

        if (!captcha.equalsIgnoreCase(realCaptcha)) {
            return Response.getFailureResponse("验证码错误或已失效，请重新获取");
        }

        note.setIpAddress(getIpAddress());
        noteService.saveNote(note);
        return Response.getSuccessResponse();
    }
}
