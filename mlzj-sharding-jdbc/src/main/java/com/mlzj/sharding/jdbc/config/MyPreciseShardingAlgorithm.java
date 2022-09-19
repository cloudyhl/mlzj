//package com.mlzj.sharding.jdbc.config;
//
//import java.util.Collection;
//import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
//import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
//
//public class MyPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
//
//    @Override
//    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
//        for (String targetName : availableTargetNames) {
//            //偶数存table_1、基数存table_2
//            String value = String.valueOf(shardingValue.getValue() % availableTargetNames.size());
//            if(targetName.endsWith(value)){
//                return targetName;
//            }
//        }
//        throw new RuntimeException("this is error for sharding ");
//    }
//}
