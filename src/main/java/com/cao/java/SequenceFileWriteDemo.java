package com.cao.java;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import java.io.IOException;
import java.net.URI;

/**
 * Created by czf on 17-7-7.
 */
public class SequenceFileWriteDemo {
    private static String [] myValue={
            "hello world",
            "bye world",
            "hello hadoop",
            "bye hadoop"
    };
    public static void main(String[] args) throws IOException{
        String uri="/home/czf/IdeaProjects/HotelTest";
        Configuration configuration=new Configuration();
        FileSystem fs=FileSystem.get(URI.create(uri),configuration);
        Path path=new Path(uri);
        IntWritable key=new IntWritable();
        Text value=new Text();
        SequenceFile.Writer writer=null;
        try {
            writer=SequenceFile.createWriter(fs,configuration,path,key.getClass(),value.getClass());
            for (int i=0;i<5000000;i++){
                key.set(5000000-i);
                value.set(myValue[i%myValue.length]);
                writer.append(key,value);
                writer.close();
            }
        }finally {
            IOUtils.closeStream(writer);
        }
    }
}
