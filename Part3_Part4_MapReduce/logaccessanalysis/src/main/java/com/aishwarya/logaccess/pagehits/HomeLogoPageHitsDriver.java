package com.aishwarya.logaccess.pagehits;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

// 1. How many hits were made to the website item “/assets/img/home-logo.png”?
public class HomeLogoPageHitsDriver {
    public void runHomeLogoPageHitsDriver(String[] args) {
        try {
            Configuration conf = new Configuration();
            conf.set("webSiteItem", args[3]);
            Job job = Job.getInstance(conf, "HomeLogoPageHits");

            job.setJarByClass(HomeLogoPageHitsDriver.class);
            job.setMapperClass(HomeLogoPageHitsMapper.class);
            job.setReducerClass(HomeLogoPageHitsReducer.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);

            FileInputFormat.addInputPath(job, new Path(args[1]));
            FileOutputFormat.setOutputPath(job, new Path(args[2]));

            boolean jobStatus = job.waitForCompletion(true);
            if (jobStatus == false) {
                System.exit(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("Exception occured : " + e.getCause());
        }
    }
}
