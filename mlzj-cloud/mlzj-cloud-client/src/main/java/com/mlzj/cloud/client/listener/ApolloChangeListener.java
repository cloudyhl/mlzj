//package com.mlzj.cloud.client.listener;
//
//import com.ctrip.framework.apollo.model.ConfigChangeEvent;
//import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
//import org.springframework.stereotype.Component;
//
///**
// * apollo 配置改变监听
// * @author yhl
// * @date 2019/6/11
// */
//@Component
//public class ApolloChangeListener {
//
//    @ApolloConfigChangeListener(value = "application")
//    public void onChange(ConfigChangeEvent configChangeEvent){
//        System.out.println(configChangeEvent.changedKeys());
//        System.out.println(configChangeEvent.getNamespace());
//        System.out.println(configChangeEvent);
//    }
//
//}
