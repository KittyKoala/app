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
    public Section findLastSection(Long novelId) {
        Example example = new Example(Section.class);
        example.createCriteria().andEqualTo("novelId", novelId);
        example.setOrderByClause("section_id desc");

        example.selectProperties("sectionId", "code", "title", "novelId", "createdTime");

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
    public List<Section> findSections(Long novelId) {
        Example example = new Example(Section.class);
        example.createCriteria().andEqualTo("novelId", novelId);

        example.selectProperties("sectionId", "novelId", "code", "title", "createdTime");

        example.setOrderByClause("section_id asc");

        return myMapper.selectByExample(example);
    }

    @Override
    public Section findSection(Long sectionId) {
        return myMapper.selectByPrimaryKey(sectionId);
    }

    @Override
    public Section findPrevSection(Long novelId, Long sectionId) {
        Example example = new Example(Section.class);
        example.createCriteria().andEqualTo("novelId", novelId).andLessThan("sectionId", sectionId);
        example.setOrderByClause("section_id desc");
        example.selectProperties("sectionId", "novelId", "code", "title", "createdTime");

        PageHelper.startPage(1, 1);
        List<Section> sections = myMapper.selectByExample(example);

        if (sections.isEmpty()) {
            return null;
        }

        return sections.get(0);
    }

    @Override
    public Section findNextSection(Long novelId, Long sectionId) {
        Example example = new Example(Section.class);
        example.createCriteria().andEqualTo("novelId", novelId).andGreaterThan("sectionId", sectionId);
        example.setOrderByClause("section_id asc");
        example.selectProperties("sectionId", "novelId", "code", "title", "createdTime");

        PageHelper.startPage(1, 1);
        List<Section> sections = myMapper.selectByExample(example);

        if (sections.isEmpty()) {
            return null;
        }

        return sections.get(0);
    }

    @Override
    public List<Section> findSectionsByPage(Long novelId, Integer pageNum) {
        Example example = new Example(Section.class);
        example.createCriteria().andEqualTo("novelId", novelId);

        example.selectProperties("sectionId", "novelId", "code", "title", "createdTime");
        example.setOrderByClause("section_id asc");

        PageHelper.startPage(pageNum, 50);
        return myMapper.selectByExample(example);
    }
}
