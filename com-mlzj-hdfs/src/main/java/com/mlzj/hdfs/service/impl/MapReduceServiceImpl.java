package com.mlzj.hdfs.service.impl;

import com.mlzj.hdfs.ComMlzjHdfsApplication;
import com.mlzj.hdfs.mapper.TextSplitCountMapper;
import com.mlzj.hdfs.reduces.TextSplitCountReducer;
import com.mlzj.hdfs.service.MapReduceService;
import java.io.IOException;
import javax.annotation.Resource;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * job需要提交数据到Hadoop执行任务
 * @author yhl
 * @date 2022/8/12
 */
@Service
public class MapReduceServiceImpl implements MapReduceService {

    @Resource
    private Job job;

    @Value("${hadoop.hdfs.default-fs}")
    private String hdfsPath;
    @Value("${hadoop.hdfs.hdfsName}")
    private String hdfsName;

    @Override
    public void startJob(String path) throws InterruptedException, IOException, ClassNotFoundException {

        org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
        configuration.set("fs.defaultFS", hdfsPath);

        job.setJarByClass(ComMlzjHdfsApplication.class);

        // 设置输入文件路径
        FileInputFormat.setInputPaths(new JobConf(configuration), path);

        // 设置Mapper属性
        job.setMapperClass(TextSplitCountMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        // 设置Reducer属性
        job.setReducerClass(TextSplitCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        // 设置输出文件路径
        FileOutputFormat.setOutputPath(new JobConf(configuration), new Path("/hdfs"));

        // 提交任务
        job.waitForCompletion(true);
    }
}
