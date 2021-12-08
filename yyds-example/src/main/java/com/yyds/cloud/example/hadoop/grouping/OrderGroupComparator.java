package com.yyds.cloud.example.hadoop.grouping;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class OrderGroupComparator extends WritableComparator {
    public OrderGroupComparator() {
        super(OrderBean.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean first = (OrderBean)a;
        OrderBean record = (OrderBean)b;
        return first.getId().compareTo(record.getId());
    }
}
