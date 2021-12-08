package com.yyds.cloud.example.hadoop.grouping;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * 订单: id, price
 * 比价订单排序, id 一样 就排序price(金额大排在前面)
 */
@Data
public class OrderBean implements WritableComparable<OrderBean> {

    private String id;
    private Double price;

    @Override
    public int compareTo(OrderBean o) {
        int i = this.id.compareTo(o.id);
        if (i == 0) {
            i = - this.price.compareTo(o.price);
        }
        return i;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(id);
        dataOutput.writeDouble(price);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.id =  dataInput.readUTF();
        this.price = dataInput.readDouble();
    }
}
