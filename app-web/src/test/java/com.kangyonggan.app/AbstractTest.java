package com.kangyonggan.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author kangyonggan
 * @since 7/19/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
@PropertySource(value = "classpath:env/dev/app.properties", encoding = "UTF-8")
public class AbstractTest {

    protected Logger log = LogManager.getLogger(AbstractTest.class);

}
