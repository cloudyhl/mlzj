package com.mlzj.storm.bolt.demo.word;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * @author yhl
 * @date 2019/5/16
 */
public class WordSplitBolt implements IRichBolt {

	private static final String ERROR_STR = "don't have a cow man";
	private static final long serialVersionUID = -8317974998795207368L;

	private OutputCollector collector;
	
	@Override
	public void prepare(Map config, TopologyContext context, OutputCollector collector) {
		 this.collector = collector;
	}
	

	@Override
	public void execute(Tuple tuple) {
	        String sentence = tuple.getStringByField("sentence");
	        String[] words = sentence.split(" ");
	        for(String word : words){
	            this.collector.emit(new Values(word));
	            //this.collector.ack(tuple);
	        }			
	}


	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));
	}
	
	@Override
	public void cleanup() {
		
	}
	
	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}


}
