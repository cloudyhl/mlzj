package com.mlzj.storm.topology.demo;

import com.mlzj.storm.bolt.demo.word.WordCountBolt;
import com.mlzj.storm.bolt.demo.word.WordReportBolt;
import com.mlzj.storm.bolt.demo.word.WordSplitBolt;
import com.mlzj.storm.spout.demo.WordSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

import java.util.concurrent.TimeUnit;

public class WordTopology {

	//定义常量
    private static final String WORD_SPOUT_ID = "word-spout";
    private static final String SPLIT_BOLT_ID = "split-bolt";
    private static final String COUNT_BOLT_ID = "count-bolt";
    private static final String REPORT_BOLT_ID = "report-bolt";
    private static final String TOPOLOGY_NAME = "word-count-topology";

    public static void main(String[] args) throws Exception {

    	//实例化对象
        WordSpout spout = new WordSpout();
        WordSplitBolt splitBolt = new WordSplitBolt();
        WordCountBolt countBolt = new WordCountBolt();
        WordReportBolt reportBolt = new WordReportBolt();

        //构建拓扑
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout(WORD_SPOUT_ID, spout);
        
        // WordSpout --> WordSplitBolt
        builder.setBolt(SPLIT_BOLT_ID, splitBolt).shuffleGrouping(WORD_SPOUT_ID);
        
        // WordSplitBolt --> WordCountBolt
        builder.setBolt(COUNT_BOLT_ID, countBolt).fieldsGrouping(SPLIT_BOLT_ID, new Fields("word"));
        
        // WordCountBolt --> WordReportBolt
        builder.setBolt(REPORT_BOLT_ID, reportBolt).globalGrouping(COUNT_BOLT_ID);

        //本地配置
        Config config = new Config();
        config.setDebug(false);
        LocalCluster cluster = new LocalCluster();

        cluster.submitTopology(TOPOLOGY_NAME, config, builder.createTopology());
        TimeUnit.SECONDS.sleep(10);
        cluster.killTopology("word-count-topology");
        cluster.shutdown();
        
    }
}
