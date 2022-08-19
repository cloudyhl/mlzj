package com.mlzj.spark.service.impl;

import com.google.common.collect.Lists;
import com.mlzj.spark.receiver.CustomReceiver;
import com.mlzj.spark.service.SparkTestService;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.input.PortableDataStream;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SparkTestServiceImpl implements SparkTestService {

    @Resource
    private JavaSparkContext javaSparkContext;

    @Resource
    private SparkSession sparkSession;

    @Override
    public void  calculateTopTen() throws InterruptedException {
        List<Integer> data = Lists.newArrayList(1,2,3,4,5,6);

        JavaRDD<Integer> rdd01 = javaSparkContext.parallelize(data);

        System.out.println(rdd01.top(2));
        //读取文件流
        JavaPairRDD<String, PortableDataStream> streamJavaPairRDD = javaSparkContext.binaryFiles("hdfs://10.220.184.231:10001/testHdfs/settings.zip");

        //读取hadoop中的文件
        JavaRDD<String> strJavaRdd = javaSparkContext.textFile("hdfs://10.220.184.231:10001/hdfs/word.txt");
//        strJavaRdd.saveAsTextFile("hdfs://10.220.184.231:10001/hdfs/word.txt");
        System.out.println(strJavaRdd.collect());

        JavaStreamingContext javaStreamingContext = new JavaStreamingContext(javaSparkContext, Durations.seconds(10));
        JavaReceiverInputDStream<String> lines = javaStreamingContext.receiverStream(new CustomReceiver(StorageLevel.MEMORY_AND_DISK_2()));

        JavaDStream<Long> count = lines.count();

        count = count.map(x -> {
            log.info("这次批一共多少条数据：{}", x);
            return x;
        });




        count.print();

        javaStreamingContext.start();

        javaStreamingContext.awaitTermination();

        javaStreamingContext.stop();
    }
}
