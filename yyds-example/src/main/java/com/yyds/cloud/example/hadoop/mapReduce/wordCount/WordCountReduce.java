package com.yyds.cloud.example.hadoop.mapReduce.wordCount;

import cn.hutool.crypto.SecureUtil;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.bouncycastle.util.Strings;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class WordCountReduce extends Reducer<Text, LongWritable, Text, LongWritable> {

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        for (LongWritable value : values) {
            count += value.get();
        }
        context.write(key, new LongWritable(count));
    }
}