package com.aishwarya.ngram;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class NGramFrequenciesMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] tokens = value.toString().split(" ");
        int ngram = Integer.parseInt(context.getConfiguration().get("ngram"));
        for (String token : tokens) {
            for (int i = 0; i < token.length() - ngram + 1; i++) {
                String ngramSeq = token.substring(i, i + ngram);
                context.write(new Text(ngramSeq), new IntWritable(1));
            }
        }
    }
}