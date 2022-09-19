package com.mlzj.sharding.jdbc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mlzj.sharding.jdbc.entity.TDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TDeptMapper extends BaseMapper<TDept> {

    void insertDept(@Param("dept") TDept tDept);
}
