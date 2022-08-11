package com.mlzj.es;

import com.mlzj.es.service.ContentService;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ComMlzjEsApplicationTests {

    @Resource
    private ContentService contentService;

    @Test
    void contextLoads() {
    }

}
