package com.mlzj.storm.bolt.demo.exception;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @author yhl
 * @date 2019/5/16
 */
public class WriterBolt implements IRichBolt {

	private static final long serialVersionUID = 1L;

	private FileWriter writer;

	private OutputCollector collector;

	@Override
	public void prepare(Map config, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		try {
			writer = new FileWriter("d://message.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean flag = false;
	
	@Override
	public void execute(Tuple tuple) {
		String word = tuple.getString(0);
//		List<String> list = (List<String>)tuple.getValueByField("word");
//		System.out.println("======================" + list);
		try {
			if(!flag && word.equals("hadoop")){
				flag = true;
				int a = 1/0;
			}
			writer.write(word);
			writer.write("\r\n");
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
			collector.fail(tuple);
		}
		collector.emit(tuple, new Values(word));
		collector.ack(tuple);
	}

	@Override
	public void cleanup() {

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {

	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
