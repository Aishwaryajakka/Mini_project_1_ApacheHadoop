package com.aishwarya.logaccess.mosthitpage;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MostHitsByPageMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        if (s.trim().length() != 0) {
            String[] tokens = s.split(" ");
            String webSiteItem = tokens[6];
            context.write(new Text(webSiteItem), new IntWritable(1));
        }
    }
}