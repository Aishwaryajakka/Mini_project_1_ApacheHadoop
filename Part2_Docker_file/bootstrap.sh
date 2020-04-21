#!/bin/bash
image_and_tag="ubuntuhadoop:1.00"
image_and_tag_array=(${image_and_tag//:/ })
if [[ "$(docker images ${image_and_tag_array[0]} | grep ${image_and_tag_array[1]} 2> /dev/null)" != "" ]]; then
  docker run $image_and_tag /bin/bash -c "bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.2.1.jar grep input output 'dfs[a-z.]+' && cat output/*"
else
  docker build -t $image_and_tag .
  docker run $image_and_tag /bin/bash -c "bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.2.1.jar grep input output 'dfs[a-z.]+' && cat output/*"
fi
