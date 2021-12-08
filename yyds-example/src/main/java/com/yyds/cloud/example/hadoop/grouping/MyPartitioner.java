package com.yyds.cloud.example.hadoop.grouping;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartitioner  extends Partitioner<OrderBean, Text> {
    @Override
    public int getPartition(OrderBean orderBean, Text nullWritable, int i) {
        //自定义分区，将相同订单id的数据发送到同一个reduce里面去
        return  (orderBean.getId().hashCode() & Integer.MAX_VALUE)%i;
    }
}
