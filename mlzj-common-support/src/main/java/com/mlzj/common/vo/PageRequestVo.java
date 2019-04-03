package com.mlzj.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 分页查询请求vo
 * @author yhl
 * @date
 */
@Data
public class PageRequestVo implements Serializable {
    private static final long serialVersionUID = 2958111945317996788L;

    /**
     * 第几页
     */
    private Integer page;
    /**
     * 页长
     */
    private Integer size;

    /**
     * 排序
     */
    private String sort;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 查询条件
     */
    private Map<String,String> conditions;
}
