package com.mlzj.commontest.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mlzj.commontest.model.OrderBean;
import com.mlzj.commontest.model.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yhl
 * @date 2020/5/11
 */
@Mapper
public interface OrderBeanDao extends BaseMapper<OrderBean> {

    /**
     * 分页对象
     * @param page 分页条件
     * @return  分页结果
     */
    IPage<Orders> selectOrders(@Param("page")IPage<Orders> page);
}
