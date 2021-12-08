package com.yyds.cloud.example.hadoop.mapReduce.wordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class WordCountRunner {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1、创建建一个job任务对象
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration, "wordcount");

        //2、指定job所在的jar包
        job.setJarByClass(WordCountRunner.class);

        //3、指定源文件的读取方式类和源文件的读取路径
        job.setInputFormatClass(TextInputFormat.class); //按照行读取
        TextInputFormat.addInputPath(job, new Path("hdfs://node1:8020/test/a")); //只需要指定源文件所在的目录即可
//        TextInputFormat.addInputPath(job, new Path("file:////Volumes/me/test/chunk/a")); //只需要指定源文件所在的目录即可

        //4、指定自定义的Mapper类和K2、V2类型
        job.setMapperClass(WordCountMapper.class); //指定Mapper类
        job.setMapOutputKeyClass(Text.class); //K2类型
        job.setMapOutputValueClass(LongWritable.class);//V2类型

        //5、指定自定义分区类（如果有的话）
        //6、指定自定义分组类（如果有的话）
        //7、指定自定义的Reducer类和K3、V3的数据类型
        job.setReducerClass(WordCountReduce.class); //指定Reducer类
        job.setOutputKeyClass(Text.class); //K3类型
        job.setOutputValueClass(LongWritable.class);  //V3类型

        //8、指定输出方式类和结果输出路径
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new  Path("hdfs://node1:8020/test/b")); //目标目录不能存在，否则报错
//        TextOutputFormat.setOutputPath(job, new  Path("file:////Volumes/me/test/chunk/c")); //目标目录不能存在，否则报错

        //9、将job提交到yarn集群
        boolean bl = job.waitForCompletion(true); //true表示可以看到任务的执行进度

        //10.退出执行进程
        System.exit(bl?0:1);
    }
}
