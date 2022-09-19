package com.mlzj.sharding.jdbc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mlzj.sharding.jdbc.entity.TDept;
import com.mlzj.sharding.jdbc.entity.TUser;

/**
 * @author yhl
 * @date 2022/9/16
 */
public interface TDeptService extends IService<TDept> {

    void addDept(TDept tDept);

}
