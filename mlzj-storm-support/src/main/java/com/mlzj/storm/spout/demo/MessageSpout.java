package com.mlzj.storm.spout.demo;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * @author yhl
 * @date 2019/5/16
 */
public class MessageSpout implements IRichSpout {

	private static final long serialVersionUID = 1L;

	private int index = 0;
	
	private String[] subjects = new String[]{
			"groovy,oeacnbase",
			"openfire,restful",
			"flume,activiti",
			"hadoop,hbase",
			"spark,sqoop"		
	};
	
	private SpoutOutputCollector collector;
	
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}
	
	@Override
	public void nextTuple() {
		if(index < subjects.length){
			String sub = subjects[index];
			//发送信息参数1 为数值， 参数2为msgId
			collector.emit(new Values(sub), index);
			index++;
		}
	}
	
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("subjects"));
	}

	@Override
	public void ack(Object msgId) {
		System.out.println("【消息发送成功!!!】 (msgId = " + msgId +")");
	}

	@Override
	public void fail(Object msgId) {
		System.out.println("【消息发送失败!!!】  (msgId = " + msgId +")");
		System.out.println("【重发进行中...】");
		collector.emit(new Values(subjects[(Integer) msgId]), msgId);
		System.out.println("【重发成功!!!】");
	}

	@Override
	public void close() {

	}

	@Override
	public void activate() {

	}

	@Override
	public void deactivate() {

	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
