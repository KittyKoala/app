package com.kangyonggan.app.service;

import com.kangyonggan.app.model.LoginLog; /**
 * @author kangyonggan
 * @since 12/6/18
 */
public interface LoginLogService {
    /**
     * 保存登录日志
     *
     * @param loginLog
     */
    void saveLoginLog(LoginLog loginLog);
}
