package com.aishwarya.ngram.runner;

import com.aishwarya.ngram.NGramFrequenciesDriver;

public class LogAccessAnalysisRunner {

    public static void main(String[] args) {
//        args = new String[4];
//        args[0]="NgramFrequencies";
//        args[1] = "/Users/aishwaryajakka/Downloads/MiniProject_1/input/ngram.txt";
//        args[2] = "/Users/aishwaryajakka/Downloads/MiniProject_1/output/NgramFrequencies";
//        args[3] = "1";

        /*args[0]="NgramFrequencies"; args[1] = "<input_file_path>";
        args[2] = "<output_file_path";args[3] = "2";*/
        if(args[0].equalsIgnoreCase("NgramFrequencies")){
            new NGramFrequenciesDriver().runNGramFrequenciesDriver(args);
        }
    }
}
