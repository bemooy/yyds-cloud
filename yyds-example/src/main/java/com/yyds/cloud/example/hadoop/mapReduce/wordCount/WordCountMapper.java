package com.yyds.cloud.example.hadoop.mapReduce.wordCount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

//keyin:  LongWritable    valuein: Text  k1 v1
//keyout: Text            valueout:IntWritable k2 v2
public class WordCountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] valueStrs = value.toString().split(",");
        for (String valueStr : valueStrs) {
            context.write(new Text(valueStr), new LongWritable(1));
        }

    }
}
