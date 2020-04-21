===================================
Part 2 - Single Node Hadoop Setup Dockerfile
===================================
Make sure bootstrap.sh is an executable (Uploaded as an executable). If its not executable, do:
- chmod +x bootstrap.sh

Make directory "~/Docker_file":
- mkdir ~/Docker_file

Copy 'bootstrap.sh' and 'Dockerfile' into the directory created above.
Make sure both 'bootstrap.sh' and 'Dockerfile' are in the same directory.

Run bootstrap.sh: Docker image with hadoop will be setup, wordcount program will run and final output will be shown:
- ./bootstrap.sh
