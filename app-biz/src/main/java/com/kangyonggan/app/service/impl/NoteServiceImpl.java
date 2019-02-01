package com.kangyonggan.app.service.impl;

import com.kangyonggan.app.model.Note;
import com.kangyonggan.app.service.NoteService;
import com.kangyonggan.common.BaseService;
import org.springframework.stereotype.Service;

/**
 * @author kangyonggan
 * @since 2019-02-01
 */
@Service
public class NoteServiceImpl extends BaseService<Note> implements NoteService {

    @Override
    public void saveNote(Note note) {
        myMapper.insertSelective(note);
    }
}
