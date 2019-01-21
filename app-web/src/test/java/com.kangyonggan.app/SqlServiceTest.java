package com.kangyonggan.app;

import com.kangyonggan.app.service.SqlService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author kangyonggan
 * @since 1/21/19
 */
public class SqlServiceTest extends AbstractTest {

    @Autowired
    private SqlService sqlService;

    /**
     * 测试SQL的执行
     *
     * @throws Exception
     */
    @Test
    public void testExecute() throws Exception {
        int rows = sqlService.execute("update tb_category set is_deleted = 0");
        log.info("rows:{}", rows);
    }

}
