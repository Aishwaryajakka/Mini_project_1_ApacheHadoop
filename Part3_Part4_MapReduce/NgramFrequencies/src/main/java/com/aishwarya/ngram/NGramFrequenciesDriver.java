package com.aishwarya.ngram;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class NGramFrequenciesDriver {
    public void runNGramFrequenciesDriver(String[] args) {
        try {
            Configuration conf = new Configuration();
            conf.set("ngram", args[3]);
            Job job = Job.getInstance(conf, "NgramFrequencies");

            job.setJarByClass(NGramFrequenciesDriver.class);
            job.setMapperClass(NGramFrequenciesMapper.class);
            job.setReducerClass(NGramFrequenciesReducer.class);

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
