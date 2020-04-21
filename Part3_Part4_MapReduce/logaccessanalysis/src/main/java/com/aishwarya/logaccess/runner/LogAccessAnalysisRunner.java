package com.aishwarya.logaccess.runner;

import com.aishwarya.logaccess.iphits.HitsByAnyIPDriver;
import com.aishwarya.logaccess.mosthitip.MostHitsByIPDriver;
import com.aishwarya.logaccess.mosthitpage.MostHitsByPageDriver;
import com.aishwarya.logaccess.pagehits.HomeLogoPageHitsDriver;

public class LogAccessAnalysisRunner {

    public static void main(String[] args) {
//        args = new String[4];
//        args[0] = "MostHitsByIP";
//        args[1] = "/Users/aishwaryajakka/Downloads/MiniProject_1/input/access_log";
//        args[2] = "/Users/aishwaryajakka/Downloads/MiniProject_1/output/logAnalysis/out";
//        args[3] = "10";

        //Question 1
        /*args[0]="HomeLogoPageHits"; args[1] = "<input_file_path>";
        args[2] = "<output_file_path";args[3] = "/assets/img/home-logo.png";*/
        if(args[0].equalsIgnoreCase("HomeLogoPageHits")){
            new HomeLogoPageHitsDriver().runHomeLogoPageHitsDriver(args);
        }
        //Question 2
        /*args[0]="HitsByAnyIP"; args[1] = "<input_file_path>";
        args[2] = "<output_file_path";args[3] = "10.153.239.5";*/
        else if(args[0].equalsIgnoreCase("HitsByAnyIP")){
            new HitsByAnyIPDriver().runHitsByAnyIPDriver(args);
        }
        //Question 3
        /*args[0]="MostHitsByPage"; args[1] = "<input_file_path>";
        args[2] = "<output_file_path";args[3] = "1";*/
        else if(args[0].equalsIgnoreCase("MostHitsByPage")){
            new MostHitsByPageDriver().runMostHitsByPageDriver(args);
        }
        //Question 4
        /*args[0]="MostHitsByIP"; args[1] = "<input_file_path>";
        args[2] = "<output_file_path";args[3] = "10";*/
        else if(args[0].equalsIgnoreCase("MostHitsByIP")){
            new MostHitsByIPDriver().runMostHitsByIPDriver(args);
        }
    }
}
