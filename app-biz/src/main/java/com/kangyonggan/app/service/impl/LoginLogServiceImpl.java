package com.kangyonggan.app.service.impl;

import com.kangyonggan.app.model.LoginLog;
import com.kangyonggan.app.service.LoginLogService;
import com.kangyonggan.common.BaseService;
import org.springframework.stereotype.Service;

/**
 * @author kangyonggan
 * @since 12/6/18
 */
@Service
public class LoginLogServiceImpl extends BaseService<LoginLog> implements LoginLogService {

    @Override
    public void saveLoginLog(LoginLog loginLog) {
        myMapper.insertSelective(loginLog);
    }
}
