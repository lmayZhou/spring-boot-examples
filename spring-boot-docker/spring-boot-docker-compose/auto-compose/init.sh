#!/bin/bash# 定义颜色BLUE_COLOR="\033[36m"RED_COLOR="\033[31m"GREEN_COLOR="\033[32m"VIOLET_COLOR="\033[35m"RES="\033[0m"echo -e "${BLUE_COLOR}# ######################################################################${RES}"echo -e "${BLUE_COLOR}#                       Docker Compose Shell Script                    #${RES}"echo -e "${BLUE_COLOR}#                       Blog: www.lmaye.com                            #${RES}"echo -e "${BLUE_COLOR}#                       Email: lmaye@lmaye.com                         #${RES}"echo -e "${BLUE_COLOR}# ######################################################################${RES}"# 创建目录echo -e "${BLUE_COLOR}---> create [mysql]directory start.${RES}"if [ ! -d "./mysql/" ]; thenmkdir -p ./mysql/datafiecho -e "${RED_COLOR}---> create [redis]directory start.${RES}"if [ ! -d "./redis/" ]; thenmkdir -p ./redis/datafiecho -e "${RED_COLOR}---> create [mongo]directory start.${RES}"if [ ! -d "./mongo/" ]; thenmkdir -p ./mongo/datafiecho -e "${BLUE_COLOR}===> create directory success.${RES}"# 目录授权(data/logs 都要授读/写权限)echo -e "${BLUE_COLOR}---> directory authorize start.${RES}"if [ -d "./mysql/" ]; thenchmod 777 ./mysql/data/fiif [ -d "./redis/" ]; thenchmod 777 ./redis/data/fiif [ -d "./mongo/" ]; thenchmod 777 ./mongo/data/fiecho -e "${BLUE_COLOR}===> directory authorize success.${RES}"# 移动配置文件echo -e "${BLUE_COLOR}---> move [mysql]config file start.${RES}"if [ -f "./my.cnf" ]; thenmv ./my.cnf ./mysqlfiecho -e "${RED_COLOR}---> move [redis]config file start.${RES}"if [ -f "./redis.conf" ]; thenmv ./redis.conf ./redisfiecho -e "${BLUE_COLOR}===> move config files success.${RES}"echo -e "${GREEN_COLOR}>>>>>>>>>>>>>>>>>> The End <<<<<<<<<<<<<<<<<<${RES}"# 部署项目echo -e "${BLUE_COLOR}==================> Docker deploy Start <==================${RES}"docker-compose up --build -d