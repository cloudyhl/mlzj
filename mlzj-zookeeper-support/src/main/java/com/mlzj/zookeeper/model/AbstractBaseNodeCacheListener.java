package com.mlzj.zookeeper.model;

import lombok.Data;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;

/**
 * nodeCache 抽象基础类
 * @author yhl
 * @date 2019/4/10
 */
@Data
public abstract class AbstractBaseNodeCacheListener implements NodeCacheListener {

    private NodeCache nodeCache;
}
