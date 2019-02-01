package com.kangyonggan.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.app.model.Note;
import com.kangyonggan.app.service.NoteService;
import com.kangyonggan.app.util.StringUtil;
import com.kangyonggan.common.BaseService;
import com.kangyonggan.common.Params;
import com.kangyonggan.common.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

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

    @Override
    public List<Note> searchNotes(Params params) {
        Example example = new Example(Note.class);
        Query query = params.getQuery();

        Example.Criteria criteria = example.createCriteria();
        String email = query.getString("email");
        if (StringUtils.isNotEmpty(email)) {
            criteria.andEqualTo("email", email);
        }
        String ipAddress = query.getString("ipAddress");
        if (StringUtils.isNotEmpty(ipAddress)) {
            criteria.andEqualTo("ipAddress", ipAddress);
        }

        String sort = params.getSort();
        String order = params.getOrder();
        if (!StringUtil.hasEmpty(sort, order)) {
            example.setOrderByClause(sort + " " + order.toUpperCase());
        } else {
            example.setOrderByClause("note_id desc");
        }

        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        return myMapper.selectByExample(example);
    }

    @Override
    public Note getNote(Long id) {
        return myMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateNote(Note note) {
        myMapper.updateByPrimaryKeySelective(note);
    }
}
