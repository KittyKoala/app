package com.kangyonggan.app.service;

/**
 * @author kangyonggan
 * @since 1/21/19
 */
public interface SqlService {

    /**
     * 执行SQL
     *
     * @param sql
     * @return
     * @throws Exception
     */
    int execute(String sql) throws Exception;

}
