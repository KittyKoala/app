package com.kangyonggan.app;

import com.kangyonggan.app.service.NovelService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author kangyonggan
 * @since 2019/1/5 0005
 */
public class NovelServiceTest extends AbstractTest {

    @Autowired
    private NovelService novelService;

    @Test
    public void testPullNovel() throws Exception {
        novelService.pullNovels("1");
        System.in.read();
    }

}
