package com.kangyonggan.app.service;

import com.kangyonggan.app.model.Note;

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
}
