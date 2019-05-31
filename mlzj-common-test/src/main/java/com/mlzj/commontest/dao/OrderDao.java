package com.mlzj.commontest.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mlzj.commontest.model.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yhl
 * @date 2019/4/26
 */
@Mapper
public interface OrderDao extends BaseMapper<Order> {
}
