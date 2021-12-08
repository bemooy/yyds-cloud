package com.yyds.cloud.example.hadoop.uploadfile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.junit.Test;

public class HDFSAPITest {

    @Test
    public void getHDFSFile() throws Exception {
        System.setProperty("hadoop.home.dir", "/Volumes/me/app/hadoop-2.7.5");
        Configuration configuration = new Configuration();
        //指定我们使用的文件系统类型:
        configuration.set("fs.defaultFS", "hdfs://node1:8020/");
        //获取指定的文件系统
        FileSystem fileSystem = FileSystem.get(configuration);
        System.out.println(">>>>"  + fileSystem.toString());
    }
}
