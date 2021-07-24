#!/bin/bash

# 定义颜色
BLUE_COLOR="\033[36m"
RED_COLOR="\033[31m"
GREEN_COLOR="\033[32m"
VIOLET_COLOR="\033[35m"
RES="\033[0m"

echo -e "${BLUE_COLOR}# ######################################################################${RES}"
echo -e "${BLUE_COLOR}#                       Docker ELK Shell Script                        #${RES}"
echo -e "${BLUE_COLOR}#                       Blog: www.lmaye.com                            #${RES}"
echo -e "${BLUE_COLOR}#                       Email: lmay@lmaye.com                          #${RES}"
echo -e "${BLUE_COLOR}# ######################################################################${RES}"

# 创建目录
echo -e "${BLUE_COLOR}---> create directory start.${RES}"
if [ ! -d "./conf/" ] && [ ! -d "./logs/" ] && [ ! -d "./data/" ]; then
mkdir -p ./conf ./logs ./data/zookeeper
fi

echo -e "${BLUE_COLOR}===> create directory success.${RES}"

# 目录授权(data/logs 都要授读/写权限)
echo -e "${BLUE_COLOR}---> directory authorize start.${RES}"
if [ -d "./logs/" ] && [ -d "./data/" ]; then
chmod 777 ./data/ ./logs/
fi

echo -e "${BLUE_COLOR}===> directory authorize success.${RES}"

# 移动配置文件
echo -e "${BLUE_COLOR}---> move config file start.${RES}"
if [ -f "./es-master.yml" ] && [ -f "./logstash.conf" ] && [ -f "./logstash.yml" ] && [ -f "./kibana.yml" ] && [ -f "./zoo.cfg" ]; then
mv ./es-master.yml ./conf
mv ./logstash.conf ./conf
mv ./logstash.yml ./conf
mv ./kibana.yml ./conf
mv ./zoo.cfg ./conf
fi

echo -e "${BLUE_COLOR}===> move config files success.${RES}"
echo -e "${GREEN_COLOR}>>>>>>>>>>>>>>>>>> The End <<<<<<<<<<<<<<<<<<${RES}"

# 部署项目
echo -e "${BLUE_COLOR}==================> Docker deploy Start <==================${RES}"
docker-compose up --build -d
