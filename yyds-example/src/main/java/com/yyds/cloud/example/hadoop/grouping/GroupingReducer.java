package com.yyds.cloud.example.hadoop.grouping;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GroupingReducer extends Reducer<OrderBean, Text,Text, NullWritable> {
    @Override
    protected void reduce(OrderBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // ArrayList<String> list = new ArrayList<>();
        // for (Text value : values) {
        //     list.add(value.toString());
        // }
        //
        // Collections.sort(list, new Comparator<String>() {
        //     @Override
        //     public int compare(String o1, String o2) {
        //         return 0;
        //     }
        // });

        //1:获取集合中的第一个元素，就是最大金额，就是K3
        //2:V3就是NullWritable
        //3:将K3和V3写入上下文中
        int count = 0;
        for (Text value : values) {
            context.write(value, NullWritable.get());
            count++;
            if(count >= 1){
                break;
            }
        }
    }
}
