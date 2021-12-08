package com.yyds.cloud.example.hadoop.grouping;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 订单: id, price
 * 比价订单排序, id 一样 就排序price(金额大排在前面)
 */
public class GroupingRunner {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1、创建建一个job任务对象
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration, "grouping_demo");

        //2、指定job所在的jar包
        job.setJarByClass(GroupingRunner.class);

        //3、指定源文件的读取方式类和源文件的读取路径
        job.setInputFormatClass(TextInputFormat.class); //按照行读取
        //TextInputFormat.addInputPath(job, new Path("hdfs://node1:8020/input/wordcount")); //只需要指定源文件所在的目录即可
        TextInputFormat.addInputPath(job, new Path("file:////Volumes/me/gitRepository/demo_text/order")); //只需要指定源文件所在的目录即可

        //4、指定自定义的Mapper类和K2、V2类型
        job.setMapperClass(GroupingMapper.class); //指定Mapper类
        job.setMapOutputKeyClass(OrderBean.class); //K2类型
        job.setMapOutputValueClass(Text.class);//V2类型

        //5、指定自定义分区类（如果有的话）
//        job.setPartitionerClass(MyPartitioner.class);
        //6、指定自定义分组类（如果有的话）
        job.setGroupingComparatorClass(OrderGroupComparator.class);
        //7、指定自定义Combiner类(如果有的话)
        //job.setCombinerClass(MyCombiner.class);


        //设置ReduceTask个数
        job.setNumReduceTasks(1);

        //8、指定自定义的Reducer类和K3、V3的数据类型
        job.setReducerClass(GroupingReducer.class); //指定Reducer类
        job.setOutputKeyClass(Text.class); //K3类型
        job.setOutputValueClass(NullWritable.class);  //V3类型

        //9、指定输出方式类和结果输出路径
        job.setOutputFormatClass(TextOutputFormat.class);
        //TextOutputFormat.setOutputPath(job, new  Path("hdfs://node1:8020/output/wordcount")); //目标目录不能存在，否则报错
        LocalDateTime now = LocalDateTime.now();
        TextOutputFormat.setOutputPath(job, new  Path("file:////Volumes/me/gitRepository/demo_text/" + now)); //目标目录不能存在，否则报错

        //10、将job提交到yarn集群
        boolean bl = job.waitForCompletion(true); //true表示可以看到任务的执行进度

        //11.退出执行进程
        System.exit(bl?0:1);
    }
}
