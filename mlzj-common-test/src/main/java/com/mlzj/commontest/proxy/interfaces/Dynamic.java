package com.mlzj.commontest.proxy.interfaces;

/**
 * @author yhl
 * @date 2019/5/29
 */
public interface Dynamic extends DynamicInterface{

    /**
     * 查询猫
     * @return 猫名字
     */
    String findSimpleCat();

    /**
     * 通过名字查询mao
     * @param name 猫名字
     * @return 猫
     */
    String findSimpleCatByName(String name);

}
