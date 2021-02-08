#!/bin/bash
source /etc/profile
/usr/local/dubbo-monitor/bin/start.sh
tail -f /usr/local/dubbo-monitor/logs/*.log
while true;do date;sleep 3600000;done