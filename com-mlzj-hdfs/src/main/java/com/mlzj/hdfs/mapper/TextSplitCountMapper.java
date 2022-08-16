package com.mlzj.hdfs.mapper;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author yhl
 * @date 2022/8/12
 */
public class TextSplitCountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 接收数据
        String line = value.toString();
        // 切分单词
        String[] words = line.split(" ");

        // 将每个单词转为数字
        for (String word : words) {
            context.write(new Text(word), new LongWritable(1));
        }
    }
}
