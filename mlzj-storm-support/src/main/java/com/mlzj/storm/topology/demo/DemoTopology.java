package com.mlzj.storm.topology.demo;

import com.mlzj.storm.bolt.demo.DemoBolt;
import com.mlzj.storm.spout.demo.DemoSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

/**
 * @author yhl
 * @date 2019/5/16
 */
public class DemoTopology {

    public static void main(String[] args) throws Exception {
        Config cfg = new Config();
        cfg.setNumWorkers(2);
        cfg.setDebug(true);


        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new DemoSpout());
        builder.setBolt("print-bolt", new DemoBolt()).shuffleGrouping("spout");


        //1 本地模式
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("top1", cfg, builder.createTopology());
		Thread.sleep(10000);
		cluster.killTopology("top1");
		cluster.shutdown();

        //2 集群模式
        //StormSubmitter.submitTopology("top1", cfg, builder.createTopology());
    }
}
