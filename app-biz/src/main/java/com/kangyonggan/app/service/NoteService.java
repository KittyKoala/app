package com.kangyonggan.app.service;

import com.kangyonggan.app.model.Note;
import com.kangyonggan.common.Params;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2019-02-01
 */
public interface NoteService {

    /**
     * 保存留言
     *
     * @param note
     */
    void saveNote(Note note);

    /**
     * 搜索留言
     *
     * @param params
     * @return
     */
    List<Note> searchNotes(Params params);

    /**
     * 获取留言
     *
     * @param id
     * @return
     */
    Note getNote(Long id);

    /**
     * 更新留言
     *
     * @param note
     */
    void updateNote(Note note);
}
