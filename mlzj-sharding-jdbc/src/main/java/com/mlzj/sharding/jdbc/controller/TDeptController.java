package com.mlzj.sharding.jdbc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mlzj.common.utils.IdGeneratorSnowflake;
import com.mlzj.sharding.jdbc.entity.TDept;
import com.mlzj.sharding.jdbc.service.TDeptService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/dept")
@RestController
@Slf4j
public class TDeptController {

    @Resource
    private TDeptService tDeptService;

    @PostMapping("/saveDept")
    public void saveUser(@RequestBody TDept tDept) {
        tDept.setId(IdGeneratorSnowflake.get().nextId());
        tDeptService.save(tDept);
    }

    @PostMapping("/saveBatch")
    public void saveBatch() {
        for (int j = 0; j < 10; j++) {
            List<TDept> addList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                TDept tDept = new TDept();
                tDept.setDeptName("部门"+j+i);
                tDept.setPosi("刚问"+j+i);
                tDept.setId(IdGeneratorSnowflake.get().nextId());
                addList.add(tDept);
            }
            tDeptService.saveBatch(addList);
        }


    }

    @GetMapping("/selectDept")
    public IPage<TDept> selectPage(@RequestParam Long page, @RequestParam Long size) {
        return tDeptService.page(new Page<>(page, size));
    }

    @GetMapping("/getById")
    public TDept getById(@RequestParam Long id) {
        return tDeptService.getById(id);
    }

    @GetMapping("/getByDept")
    public TDept getByName(@RequestParam String name) {
        LambdaQueryWrapper<TDept> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(TDept::getDeptName, name);
        return tDeptService.getOne(queryWrapper);
    }

}
