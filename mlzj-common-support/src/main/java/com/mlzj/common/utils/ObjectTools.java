package com.mlzj.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.springframework.beans.BeanUtils;

/**
 * @author yhl
 * @date 2022/11/28
 */
public class ObjectTools {

    private ObjectTools() {}

    /**
     * 获取对象复制
     * @param obj 请求数据对象
     * @param tClass 目标生成对象的类型
     * @param <T> 泛型
     * @return 目标对象
     */
    public <T> T getCopyObj(Object obj, Class<T> tClass) throws Exception {
        T targetObj = tClass.getDeclaredConstructor().newInstance();
        BeanUtils.copyProperties(obj, targetObj);
        return targetObj;
    }

    /**
     * 获取对象复制
     * @param dataObjs 请求数据对象
     * @param tClass 目标生成对象的类型
     * @param <T> 泛型
     * @return 目标对象
     */
    public <T> List<T> getCopyObjList(List<?> dataObjs, Class<T> tClass) throws Exception {
        List<T> resultList = new ArrayList<>();
        for (Object dataObj : dataObjs) {
            T targetObj = tClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(dataObj, targetObj);
            resultList.add(targetObj);
        }
        return resultList;
    }

    /**
     * 获取对象复制
     * @param dataObjs 请求数据对象
     * @param tClass 目标生成对象的类型
     * @param <T> 泛型
     * @return 目标对象
     */
    public <T> List<T> getCopyObjListDoAfter(List<?> dataObjs, Class<T> tClass, Consumer<T> tConsumer) throws Exception {
        List<T> copyObjList = this.getCopyObjList(dataObjs, tClass);
        copyObjList.forEach(tConsumer);
        return copyObjList;
    }


}
