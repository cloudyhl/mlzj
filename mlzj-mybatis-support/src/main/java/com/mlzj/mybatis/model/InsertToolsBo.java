package com.mlzj.mybatis.model;

import java.util.List;
import lombok.Data;

/**
 * @author yhl
 * @date 2022/11/21
 */
@Data
public class InsertToolsBo {

    /**
     * sql语句
     */
    private String sql;

    /**
     * 插入的普通对象
     */
    private Object object;

    /**
     * 插入的list对象
     */
    private List<Object> insertList;

}
