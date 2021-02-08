#!/bin/bash
source /etc/profile
nohup java -Xms512M -Xmx512M -Xss1024K -XX:PermSize=256m -XX:MaxPermSize=512m -cp /kafka-monitor.jar com.quantifind.kafka.offsetapp.OffsetGetterWeb  --zk  middle-1:2181,middle-2:2181,middle-3:2181/ --port 19092 --refresh 10.
seconds --retain 1.days --offsetStorage kafka & tail -f ./nohup.out