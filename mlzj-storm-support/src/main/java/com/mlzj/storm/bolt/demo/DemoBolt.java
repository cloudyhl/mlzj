package com.mlzj.storm.bolt.demo;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

/**
 * @author yhl
 * @date 2019/5/15
 */
public class DemoBolt extends BaseBasicBolt {
    private static final long serialVersionUID = -5352631743783885530L;

    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        String demoValue = tuple.getStringByField("demoValue");
        System.out.println(demoValue);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
