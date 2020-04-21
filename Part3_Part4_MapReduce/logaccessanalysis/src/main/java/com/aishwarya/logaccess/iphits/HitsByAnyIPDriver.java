package com.aishwarya.logaccess.iphits;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

// 2. How many hits were made from the IP: 10.153.239.5 ?
public class HitsByAnyIPDriver {
    public void runHitsByAnyIPDriver(String[] args) {
        try {
            Configuration conf = new Configuration();
            conf.set("ip", args[3]);
            Job job = Job.getInstance(conf, "HitsByAnyIP");

            job.setJarByClass(HitsByAnyIPDriver.class);
            job.setMapperClass(HitsByAnyIPMapper.class);
            job.setReducerClass(HitsByAnyIPReducer.class);

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
