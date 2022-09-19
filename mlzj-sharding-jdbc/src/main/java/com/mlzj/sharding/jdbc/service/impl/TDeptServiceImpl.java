package com.mlzj.sharding.jdbc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mlzj.sharding.jdbc.dao.TDeptMapper;
import com.mlzj.sharding.jdbc.entity.TDept;
import com.mlzj.sharding.jdbc.service.TDeptService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TDeptServiceImpl extends ServiceImpl<TDeptMapper, TDept> implements TDeptService {

    @Resource
    private TDeptMapper tDeptMapper;

    @Override
    public void addDept(TDept tDept) {
        tDeptMapper.insertDept(tDept);
    }
}
