package com.kangyonggan.app.controller.dashboard.sites;

import com.kangyonggan.app.annotation.PermissionMenu;
import com.kangyonggan.app.annotation.Token;
import com.kangyonggan.app.constants.YesNo;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Note;
import com.kangyonggan.app.service.EmailService;
import com.kangyonggan.app.service.NoteService;
import com.kangyonggan.common.Page;
import com.kangyonggan.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author kangyonggan
 * @since 1/4/19
 */
@Controller
@RequestMapping("dashboard/sites/note")
public class DashboardSitesNoteController extends BaseController {

    private static final String PATH_ROOT = "dashboard/sites/note";

    @Autowired
    private NoteService noteService;

    @Autowired
    private EmailService emailService;

    /**
     * 留言列表界面
     *
     * @return
     */
    @GetMapping
    @PermissionMenu("SITES_NOTE")
    public String index() {
        return PATH_ROOT + "/list";
    }

    /**
     * 留言列表查询
     *
     * @return
     */
    @GetMapping("list")
    @PermissionMenu("SITES_NOTE")
    @ResponseBody
    public Page<Note> list() {
        return new Page<>(noteService.searchNotes(getRequestParams()));
    }

    /**
     * 回复
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("{id:[\\d]+}/reply")
    @PermissionMenu("SITES_NOTE")
    @Token(key = "replyNote")
    public String replyNote(@PathVariable("id") Long id, Model model) {
        model.addAttribute("note", noteService.getNote(id));
        return PATH_ROOT + "/reply-modal";
    }

    /**
     * 回复
     *
     * @param id
     * @param content
     * @return
     */
    @PostMapping("{id:[\\d]+}/reply")
    @ResponseBody
    @PermissionMenu("SITES_NOTE")
    @Token(key = "replyNote", type = Token.Type.CHECK)
    public Response replyNote(@PathVariable("id") Long id, @RequestParam("content") String content) {
        Note note = noteService.getNote(id);
        emailService.sendEmail(note.getEmail(), "您给东方骄子的留言：" + note.getContent() + "\n\n站长回复：\n" + content, getIpAddress());
        note.setIsReply(YesNo.YES.getCode());
        noteService.updateNote(note);
        return Response.getSuccessResponse();
    }
}
