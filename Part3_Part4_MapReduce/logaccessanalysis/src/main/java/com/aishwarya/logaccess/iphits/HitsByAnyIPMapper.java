package com.aishwarya.logaccess.iphits;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class HitsByAnyIPMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        //10.223.157.186 - - [15/Jul/2009:15:50:35 -0700] "GET /assets/js/lowpro.js HTTP/1.1" 200 10469
        if (s.trim().length() != 0) {
            String[] tokens = s.split(" ");
            String webSiteItem = tokens[0];
            if (webSiteItem.equalsIgnoreCase(context.getConfiguration().get("ip"))) {
                context.write(new Text(webSiteItem), new IntWritable(1));
            }

        }
    }

   /*
    // test sample for proper token
    public static void main(String[] args) {
        String s = "10.223.157.186 - - [15/Jul/2009:15:50:35 -0700] \"GET /assets/js/lowpro.js HTTP/1.1\" 200 10469";
        if (s.trim().length() != 0) {
            String[] tokens = s.split(" ");
            String webSiteItem = tokens[6];
            System.out.printf(webSiteItem);

        }
    }*/
}