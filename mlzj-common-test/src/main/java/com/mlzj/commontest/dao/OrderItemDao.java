package com.mlzj.commontest.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mlzj.commontest.model.OrderItemBean;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yhl
 * @date 2020/5/11
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemBean> {
}
