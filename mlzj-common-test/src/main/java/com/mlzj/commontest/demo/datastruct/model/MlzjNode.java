package com.mlzj.commontest.demo.datastruct.model;

import lombok.Data;

/**
 * @author yhl
 * @date 2019/10/11
 */
@Data
public class MlzjNode<T> {

    private MlzjNode<T> previous;

    private T value;

    private MlzjNode<T> next;
}
