package com.kangyonggan.app.service.impl;

import com.github.ofofs.jca.annotation.Log;
import com.github.pagehelper.PageHelper;
import com.kangyonggan.app.constants.AppConstants;
import com.kangyonggan.app.constants.YesNo;
import com.kangyonggan.app.exception.ValidException;
import com.kangyonggan.app.model.Record;
import com.kangyonggan.app.service.RecordService;
import com.kangyonggan.app.util.StringUtil;
import com.kangyonggan.common.BaseService;
import com.kangyonggan.common.Params;
import com.kangyonggan.common.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @since 10/3/18
 */
@Service
public class RecordServiceImpl extends BaseService<Record> implements RecordService {

    @Override
    @Log
    public void saveRecord(Record record) {
        if (StringUtils.isEmpty(record.getContent()) && StringUtils.isEmpty(record.getFileNames())) {
            throw new ValidException();
        }
        if (StringUtils.isEmpty(record.getContent())) {
            record.setContent("");
        }
        myMapper.insertSelective(record);
    }

    @Override
    @Log
    public List<Record> findRecords(String openid, int pageNum) {
        Example example = new Example(Record.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", YesNo.NO.getCode());
        if (StringUtils.isNotEmpty(openid)) {
            criteria.andEqualTo("openid", openid);
        }

        example.setOrderByClause("id desc");
        PageHelper.startPage(pageNum, AppConstants.SALT_SIZE);
        return myMapper.selectByExample(example);
    }

    @Override
    @Log
    public Record findRecordById(Long id) {
        return myMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Record> searchRecords(Params params) {
        Example example = new Example(Record.class);
        Query query = params.getQuery();
        Example.Criteria criteria = example.createCriteria();

        String openid = query.getString("openid");
        if (StringUtils.isNotEmpty(openid)) {
            criteria.andEqualTo("openid", openid);
        }

        String content = query.getString("content");
        if (StringUtils.isNotEmpty(content)) {
            criteria.andLike("content", StringUtil.toLike(content));
        }

        Date beginDate = query.getDate("beginDate");
        if (beginDate != null) {
            criteria.andGreaterThanOrEqualTo("createdTime", beginDate);
        }
        Date endDate = query.getDate("endDate");
        if (endDate != null) {
            criteria.andLessThanOrEqualTo("createdTime", endDate);
        }

        String sort = params.getSort();
        String order = params.getOrder();
        if (!StringUtil.hasEmpty(sort, order)) {
            example.setOrderByClause(sort + " " + order.toUpperCase());
        } else {
            example.setOrderByClause("id DESC");
        }

        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        return myMapper.selectByExample(example);
    }

    @Override
    public void updateRecord(Record record) {
        myMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void deleteRecords(String recordIds) {
        if (StringUtils.isEmpty(recordIds)) {
            return;
        }
        String[] ids = recordIds.split(",");
        Example example = new Example(Record.class);
        example.createCriteria().andIn("id", Arrays.asList(ids));

        Record record = new Record();
        record.setIsDeleted(YesNo.YES.getCode());

        myMapper.updateByExampleSelective(record, example);
    }

    @Override
    public void restoreRecords(String recordIds) {
        if (StringUtils.isEmpty(recordIds)) {
            return;
        }
        String[] ids = recordIds.split(",");
        Example example = new Example(Record.class);
        example.createCriteria().andIn("id", Arrays.asList(ids));

        Record record = new Record();
        record.setIsDeleted(YesNo.NO.getCode());

        myMapper.updateByExampleSelective(record, example);
    }
}
