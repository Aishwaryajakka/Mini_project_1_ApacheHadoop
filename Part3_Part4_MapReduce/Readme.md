==========================
Preparations for Part 3 and Part 4:
==========================

1. Copy relevant files to VM:

In Master VM:

- mkdir ~/Mini_proj_1

Open terminal from local system (outside VM) where you downloaded the files.
On terminal, change into directory of the files and paste the commands below:

- sudo scp -i $HOME/.ssh/key_student ngram-frequencies.jar student@165.227.73.164:~/Mini_proj_1/

- sudo scp -i $HOME/.ssh/key_student ngram.txt student@165.227.73.164:~/Mini_proj_1/

- sudo scp -i $HOME/.ssh/key_student access_log student@165.227.73.164:~/Mini_proj_1/

- sudo scp -i $HOME/.ssh/key_student logaccess-analysis.jar student@165.227.73.164:~/Mini_proj_1/


2. Start Hadoop:

- start-all.sh

3. Make Mini_proj_1 directory on hdfs:

- hdfs dfs -mkdir Mini_proj_1
- hdfs dfs -mkdir Mini_proj_1/input

4. Put input files onto hdfs

- hdfs dfs -put access_log Mini_proj_1/input
- hdfs dfs -put ngram.txt Mini_proj_1/input


====================
Part 3: N-Gram program:
====================

1. DESCRIPTION OF ARGUMENTS:  

Arg[0] - class name  
Arg[1] – Input file path  
Arg[2] – Output file path 
Arg[3] – frequency (n=1,2 or3)

Please note: 
Comment the below lines of code in the main program before packaging into a jar.  

To run locally on IntelliJ, please uncomment and assign the following arguments as applicable & run the main class. The output would be in the path mentioned in arg[2]. 

"args = new String[4]; 
args[0]="NgramFrequencies"; 
args[1] = "/Users/aishwaryajakka/Downloads/MiniProject_1/input/ngram.txt"; 
args[2] = "/Users/aishwaryajakka/Downloads/MiniProject_1/output/NgramFrequencies"; 
args[3] = "1";" 


2. BUILDING THE JAR: 

Pom file points to the following : Please change the pom file as applicable. 
Main class:  com.aishwarya.ngram.runner.LogAccessAnalysisRunner 
Hadoop Version: 3.2.1 
Java JDK: 1.8 

Open terminal and navigate the project directory, and type 

- mvn clean install 

When the build is successful, the jar is available under “<project_folder_path>/ NgramFrequencies /target/ ngram-frequencies.jar”

3. FIRST TIME EXECUTION STEPS ON HADOOP CLUSTER (Ignore if already done in Preparations section): 

“<project_folder_path>/input/ngram.txt” is the input file used to test this program. This file needs to be copied into the Master’s Virtual Machine.
- sudo scp -i $HOME/.ssh/key_student ngram-frequencies.jar student@165.227.73.164:~/Mini_proj_1/

Also, the ngram-frequencies.jar needs to be copied to the Master’s virtual machine
- sudo scp -i $HOME/.ssh/key_student ngram-frequencies.jar student@165.227.73.164:~/Mini_proj_1/
 
Log onto the Master’s VM & execute the following commands to put the input files into hdfs. 

- hdfs dfs -mkdir Mini_proj_1 
- hdfs dfs -mkdir Mini_proj_1/input 
- hdfs dfs -put ngram.txt Mini_proj_1/input

4. COMMANDS:

1. Command to run the program:

N=1
- hadoop jar ngram-frequencies.jar NgramFrequencies Mini_proj_1/input/ngram.txt Mini_proj_1/output/NGramFrequencies 1

N=2
- hadoop jar ngram-frequencies.jar NgramFrequencies Mini_proj_1/input/ngram.txt Mini_proj_1/output/NGramFrequencies 2

N=3
- hadoop jar ngram-frequencies.jar NgramFrequencies Mini_proj_1/input/ngram.txt Mini_proj_1/output/NGramFrequencies 3

2. Command to display output:
- hdfs dfs -cat Mini_proj_1/output/NGramFrequencies/part-r-00000

3. Command to remove output folder:
- hdfs dfs -rmr Mini_proj_1/output


==================
Part 4 - Logs program
==================

1. DESCRIPTION OF THE ARGUMENTS:  

Arg[0] - class name  
Arg[1] – Input file path  
Arg[2] – Output file path 
Arg[3] – Applicable parameter (IP address/ website path) 

Please note: 

Comment the below lines of code in the main program before packaging into a jar.  

To run locally on IntelliJ, please uncomment and assign the following arguments as applicable & run the main class. The output would be in the path mentioned in arg[2]. 

"args = new String[4]; 
args[0] = "MostHitsByIP"; 
args[1] = "/Users/aishwaryajakka/Downloads/MiniProject_1/input/access_log"; 
args[2] = "/Users/aishwaryajakka/Downloads/MiniProject_1/output/logAnalysis/ MostHitsByIP "; 
args[3] = "10"; 

The value of arg[0] , args[2] and arg[3] will change according to the question asked. 
 
2. BUILDING THE JAR: 

Pom file points to the following: Please change the pom file as applicable. 

Main class: com.aishwarya.logaccess.runner.LogAccessAnalysisRunner 
Hadoop Version: 3.2.1 
Java JDK: 1.8 

Open terminal and navigate to the project directory, and type 

- mvn clean install 

When the build is successful, the jar is available under “<project_folder_path>/ logaccessanalysis /target/ logaccess-analysis.jar” 

3. FIRST TIME EXECUTION STEPS ON HADOOP CLUSTER (Ignore if already done in Preparations section):

“<project_folder_path>/input/access_log” is the input file used to test this program. This file needs to be copied into the master’s Virtual Machine.
- sudo scp -i $HOME/.ssh/key_student access_log student@165.227.73.164:~/Mini_proj_1/

Also, the logaccess-analysis.jar needs to be copied to the master’s virtual machine 
- sudo scp -i $HOME/.ssh/key_student logaccess-analysis.jar student@165.227.73.164:~/Mini_proj_1/

=============================================================
Q1. How many hits were made to the website item “/assets/img/home-logo.png”?
=============================================================

Hits: 98744

Argument 4: website item
Arguments: args[0]="HomeLogoPageHits"; args[1] = "<input_file_path>"; args[2] = "<output_file_path>” ;args[3] = "/assets/img/home-logo.png";

Command to run the program:
- hadoop jar logaccess-analysis.jar HomeLogoPageHits Mini_proj_1/input/access_log Mini_proj_1/output/HomeLogoPageHits /assets/img/home-logo.png

Command to display output:
- hdfs dfs -cat Mini_proj_1/output/HomeLogoPageHits/part-r-00000

==========================================
Q2. How many hits were made from the IP: 10.153.239.5
==========================================

Hits: 547

Argument 4: Specified IP
Arguments: args[0]="HitsByAnyIP"; args[1] = "<input_file_path>"; args[2] = "<output_file_path>” ;args[3] = "10.153.239.5";

Command to run the program:
- hadoop jar logaccess-analysis.jar HitsByAnyIP Mini_proj_1/input/access_log Mini_proj_1/output/HitsByAnyIP 10.153.239.5

Command to display output:
- hdfs dfs -cat Mini_proj_1/output/HitsByAnyIP/part-r-00000

===================================================================
Q3. Which path in the website has been hit most? How many hits were made to the path?
===================================================================

Path: http://www.the-associates.co.uk/trailers/?p=1&r=&l=&o=/
Number of hits: 117348

Argument 4: Dummy count variable (Should be >0)
Arguments: args[0]="MostHitsByPage"; args[1] = "<input_file_path>"; args[2] = "<output_file_path>”; args[3] = "1";

Command to run the program:
- hadoop jar logaccess-analysis.jar MostHitsByPage Mini_proj_1/input/access_log Mini_proj_1/output/MostHitsByPage 1

Command to display output:
- hdfs dfs -cat Mini_proj_1/output/MostHitsByPage/part-r-00000

============================================================
Q4. Which IP accesses the website most? How many accesses were made by it?
============================================================

IP: 10.216.113.172
Number of Accesses: 158614

Argument 4: Number of IPs listed in descending number of accesses. For just highest accessing IP, input 1.

Arguments: args[0]="MostHitsByIP"; args[1] = "<input_file_path>"; args[2] = "<output_file_path";args[3] = "1";

Command to run the program:
- hadoop jar logaccess-analysis.jar MostHitsByIP Mini_proj_1/input/access_log Mini_proj_1/output/MostHitsByIP 1

Command to display output:
- hdfs dfs -cat Mini_proj_1/output/MostHitsByIP/part-r-00000


==================
To Stop Hadoop Cluster:
==================
- stop-all.sh
