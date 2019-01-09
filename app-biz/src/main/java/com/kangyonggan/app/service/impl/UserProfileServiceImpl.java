package com.kangyonggan.app.service.impl;

import com.kangyonggan.app.exception.ValidException;
import com.kangyonggan.app.model.UserProfile;
import com.kangyonggan.app.service.UserProfileService;
import com.kangyonggan.app.util.IdNoUtil;
import com.kangyonggan.app.util.ValidUtil;
import com.kangyonggan.common.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author kangyonggan
 * @since 2018/12/9 0009
 */
@Service
public class UserProfileServiceImpl extends BaseService<UserProfile> implements UserProfileService {

    @Override
    public void updateUserProfile(UserProfile userProfile) {
        ValidUtil.valid(userProfile);

        if (StringUtils.isNotEmpty(userProfile.getIdNo())) {
            if (!IdNoUtil.isIdCard18(userProfile.getIdNo())) {
                throw new ValidException("请输入合法身份证号码");
            }
        }

        Example example = new Example(UserProfile.class);
        example.createCriteria().andEqualTo("userId", userProfile.getUserId());

        myMapper.updateByExampleSelective(userProfile, example);
    }

    @Override
    public void saveUserProfile(UserProfile userProfile) {
        myMapper.insertSelective(userProfile);
    }
}
