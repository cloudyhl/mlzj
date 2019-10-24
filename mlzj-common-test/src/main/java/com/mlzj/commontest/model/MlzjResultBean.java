package com.mlzj.commontest.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yhl
 * @date 2019/9/19
 */
public class MlzjResultBean {

    private List<Object> resultList = new ArrayList<>();

    public MlzjResultBean addResult(Object obj){
        resultList.add(obj);
        return this;
    }
    public Object getResult(int index){
        return resultList.get(index);
    }

    public static MlzjResultBean getInstance(){
        return new MlzjResultBean();
    }

}
