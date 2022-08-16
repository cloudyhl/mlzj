package com.mlzj.hive.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mlzj.hive.entity.Word;
import com.mlzj.hive.service.WordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhl
 * @date 2022/8/15
 */
@Api(value = "/hive", tags = "hive")
@Slf4j
@RestController
@RequestMapping("/hive")
public class HiveController {

    @Resource
    private WordService wordService;

    @GetMapping("/queryAll")
    public String queryAll() {
        Page<Word> page = wordService.page(new Page<>(1, 2));
        System.out.println(page);
        List<Word> list = wordService.list();
        return list.get(0).getWords();
    }


    @PostMapping("/save")
    public void save() {
        Word word = new Word();
        word.setWords("nihao nihao hahahaha");
        wordService.save(word);
    }

    @ApiOperation(value = "从HDFS读取数据覆盖原表数据")
    @PostMapping("/loadPathOverwrite")
    public void loadPathOverwrite(@RequestParam String path, @RequestParam String tableName) {
        wordService.loadPathOverwrite(path, tableName);
    }

    @ApiOperation(value = "从HDFS读取数据追加原表数据")
    @PostMapping("/loadPathInto")
    public void loadPathInto(@RequestParam String path, @RequestParam String tableName) {
        wordService.loadPathInto(path, tableName);
    }
}
