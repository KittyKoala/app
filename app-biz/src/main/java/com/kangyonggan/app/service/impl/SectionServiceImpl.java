package com.kangyonggan.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.app.model.Section;
import com.kangyonggan.app.service.SectionService;
import com.kangyonggan.common.BaseService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2019/1/5 0005
 */
@Service
public class SectionServiceImpl extends BaseService<Section> implements SectionService {

    @Override
    public Section findLastSection(String novelCode) {
        Example example = new Example(Section.class);
        example.createCriteria().andEqualTo("novelCode", novelCode);
        example.setOrderByClause("code desc");

        example.selectProperties("code", "title", "novelCode");

        PageHelper.startPage(1, 1);
        List<Section> sections = myMapper.selectByExample(example);
        if (sections.isEmpty()) {
            return null;
        }
        return sections.get(0);
    }

    @Override
    public void saveSection(Section section) {
        myMapper.insertSelective(section);
    }

    @Override
    public List<Section> findSections(String novelCode) {
        Example example = new Example(Section.class);
        example.createCriteria().andEqualTo("novelCode", novelCode);

        example.selectProperties("sectionId", "novelCode", "code", "title", "createdTime");

        example.setOrderByClause("code desc");

        return myMapper.selectByExample(example);
    }
}
