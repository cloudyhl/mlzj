package com.mlzj.hbase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mlzj.hbase.dto.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yhl
 * @date 2022/8/29
 */
@Mapper
public interface UserEntityMapper extends BaseMapper<UserEntity> {

    @Insert("UPSERT INTO \"hbase_test\".\"user\" VALUES( #{id}, #{userName}, #{birthday} )")
    void insertUserEntity(UserEntity userEntity);

}
