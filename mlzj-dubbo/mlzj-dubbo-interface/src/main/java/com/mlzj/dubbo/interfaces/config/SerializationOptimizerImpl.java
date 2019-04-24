package com.mlzj.dubbo.interfaces.config;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;
import com.mlzj.dubbo.interfaces.vo.User;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yhl
 * @date 2019/4/21
 */
public class SerializationOptimizerImpl implements SerializationOptimizer {

    public Collection<Class> getSerializableClasses() {
        List<Class> classes = new LinkedList<Class>();
        classes.add(User.class);
        return classes;
    }
}
