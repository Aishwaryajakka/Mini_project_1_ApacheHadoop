package com.aishwarya.logaccess.mosthitip;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MostHitsByIPReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private Map<Text, IntWritable> countMap = new HashMap<>();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values) {
            sum = sum + value.get();
        }
        countMap.put(new Text(key), new IntWritable(sum));
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        Map<Text, IntWritable> sortedMap = sortByValues(countMap);
        int counter = 0;
        int countN = Integer.parseInt(context.getConfiguration().get("topNCount"));
        for (Text key : sortedMap.keySet()) {
            if (counter++ == countN) {
                break;
            }
            context.write(key, sortedMap.get(key));
        }
    }

    public Map<Text, IntWritable> sortByValues(Map<Text, IntWritable> map) {
        return map.entrySet()
                .stream()
                .sorted((Map.Entry.<Text, IntWritable>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
