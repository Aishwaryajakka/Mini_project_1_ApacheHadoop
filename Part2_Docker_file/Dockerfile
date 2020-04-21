# Set the base image ubuntu for this Docker image
FROM ubuntu:18.04

# Set id of maintainer of image
MAINTAINER shreyank ranganath <shr74@pitt.edu>

# Assigning user to run system calls
USER root

# Install updates and tools
RUN apt update
RUN apt install -y openjdk-8-jdk
RUN apt-get install -y curl tar sudo openssh-server openssh-client rsync

# Download and unzip Hadoop

WORKDIR /root
RUN wget http://mirrors.advancedhosters.com/apache/hadoop/common/hadoop-3.2.1/hadoop-3.2.1.tar.gz
RUN tar -zxf hadoop-3.2.1.tar.gz
RUN ln -s hadoop-3.2.1 hadoop
WORKDIR hadoop

# Setup Hadoop

RUN echo "export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64">> etc/hadoop/hadoop-env.sh
RUN mkdir input
RUN cp etc/hadoop/*.xml input

CMD ["echo", "Image Successfully created. Attempt word count example..."]
