package com.mlzj.commontest.model;

import java.util.*;

/**
 * @author yhl
 * @date 2019/9/19
 */
public class MlzjParameterBean {

    private Map<String, Object> paramMap = new HashMap<>(4);

    /**
     * 参数集合
     */
    private List<Object> parametersList = new ArrayList<>();

    public MlzjParameterBean addParameter(Object obj){
        parametersList.add(obj);
        return this;
    }

    public Object getParam(int index){
        return parametersList.get(index);
    }

    public MlzjParameterBean addParameters(Object...objects){
        parametersList.addAll(Arrays.asList(objects));
        return this;
    }


}
