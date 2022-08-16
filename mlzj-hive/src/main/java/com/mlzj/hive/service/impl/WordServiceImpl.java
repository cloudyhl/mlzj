package com.mlzj.hive.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mlzj.hive.dao.WordMapper;
import com.mlzj.hive.entity.Word;
import com.mlzj.hive.service.WordService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author yhl
 * @date 2022/8/5
 */
@Service
public class WordServiceImpl extends ServiceImpl<WordMapper, Word> implements WordService {

    @Resource
    private WordMapper wordMapper;

    @Override
    public void loadPathOverwrite (String path, String tableName) {
        wordMapper.loadPathOverwrite(path, tableName);
    }

    @Override
    public void loadPathInto(String path, String tableName) {
        wordMapper.loadPathInto(path, tableName);
    }
}
