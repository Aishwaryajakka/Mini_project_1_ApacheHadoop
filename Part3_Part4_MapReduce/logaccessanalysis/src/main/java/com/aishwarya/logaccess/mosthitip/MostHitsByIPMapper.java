package com.aishwarya.logaccess.mosthitip;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MostHitsByIPMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        if (s.trim().length() != 0) {
            String[] tokens = s.split(" ");
            String webSiteItem = tokens[0];
            context.write(new Text(webSiteItem), new IntWritable(1));
        }
    }
}