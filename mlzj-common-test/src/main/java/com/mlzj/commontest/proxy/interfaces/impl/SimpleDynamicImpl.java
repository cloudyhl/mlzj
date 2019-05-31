package com.mlzj.commontest.proxy.interfaces.impl;

import com.mlzj.commontest.proxy.interfaces.DynamicInterface;

/**
 * @author yhl
 * @date 2019/5/29
 */
public class SimpleDynamicImpl implements DynamicInterface {


    @Override
    public String baseSelect() {
        return "this is baseSelect method";
    }
}
