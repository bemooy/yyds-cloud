package com.yyds.cloud.example.hadoop.grouping;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class GroupingMapper extends Mapper<LongWritable, Text,OrderBean,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1:得到K2（OrderBean）
        String[] split = value.toString().split(",");

        OrderBean orderBean = new OrderBean();
        orderBean.setId(split[0]);
        orderBean.setPrice(Double.parseDouble(split[2]));

        //2:得到V2（行文本数据--value）
        //3:将K2和V2写入上下文中
        context.write(orderBean,value);
    }
}
