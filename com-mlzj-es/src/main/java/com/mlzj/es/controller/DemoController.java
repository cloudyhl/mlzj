package com.mlzj.es.controller;


import com.mlzj.es.dto.TitleContentEntity;
import com.mlzj.es.service.ContentService;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhl
 * @date 20201/4/13
 */
@RestController
@Slf4j
public class DemoController {

    @Resource
    private ContentService contentService;


    @GetMapping("/sleep")
    public String sleep() throws InterruptedException {
        log.info("go in");
        TimeUnit.SECONDS.sleep(120);
        log.info("---------------------------------------------");
        return "Sleep finish";
    }


    @GetMapping("/ok")
    public String ok() throws InterruptedException {
        return "ok";
    }

    @PostMapping("/addContent")
    public String addContent(@RequestBody TitleContentEntity titleContentEntity) throws InterruptedException {
        contentService.save(titleContentEntity);
        return "ok";
    }

    @PostMapping("/findContent")
    public List<TitleContentEntity> findContent(@RequestParam String queryStr) throws InterruptedException {
        return contentService.findContent(queryStr);
    }


}
