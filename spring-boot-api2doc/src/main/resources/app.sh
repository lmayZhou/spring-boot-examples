#!/bin/bash

# 定义颜色
BLUE_COLOR="\033[36m"
RED_COLOR="\033[31m"
GREEN_COLOR="\033[32m"
VIOLET_COLOR="\033[35m"
RES="\033[0m"

# 程序名称(只需修改此处)
APP_NAME=spring-boot-api2doc-1.0.1-SNAPSHOT.jar

echo -e "${BLUE_COLOR}# ######################################################################${RES}"
echo -e "${BLUE_COLOR}#                       ${APP_NAME} Shell Script                       #${RES}"
echo -e "${BLUE_COLOR}#                       Blog: www.lmaye.com                            #${RES}"
echo -e "${BLUE_COLOR}#                       Email: lmaye@lmaye.com                         #${RES}"
echo -e "${BLUE_COLOR}# ######################################################################${RES}"

# 使用说明，提示输入参数
usage() {
    echo -e "${VIOLET_COLOR}Usage: sh app.sh [start | stop | restart | status]${RES}"
    echo -e "${BLUE_COLOR}------->    stop: 停止${RES}"
    echo -e "${BLUE_COLOR}------->   start: 启动${RES}"
    echo -e "${BLUE_COLOR}------->  status: 查看状态${RES}"
    echo -e "${BLUE_COLOR}-------> restart: 重启${RES}"
    exit 1
}

# 检查程序运行线程是否存在
is_exist() {
    pid=`ps -ef | grep ${APP_NAME} | grep -v grep | grep -v kill | awk '{print $2}'`
    # 如果不存在返回0，存在则返回1
    if [ -z "${pid}" ]; then
        return 0
    else
        return 1
    fi
}

# 启动
start() {
    is_exist
    if [ $? -eq "1" ]; then
        echo -e "${RED_COLOR}${APP_NAME} is already running. Pid = ${pid}${RES}"
    else
        nohup java -jar /home/${APP_NAME} > app.log 2>&1 &
        echo -e "${BLUE_COLOR}${APP_NAME} start success.${RES}"
    fi
}

# 停止
stop() {
    is_exist
    if [ $? -eq "1" ]; then
        kill -9 ${pid}
    else
        echo -e "${RED_COLOR}${APP_NAME} is not running.${RES}"
    fi
}

# 运行状态
status(){
    is_exist
    if [ $? -eq "1" ]; then
        echo -e "${GREEN_COLOR}${APP_NAME} is running. Pid = ${pid}${RES}"
    else
        echo -e "${RED_COLOR}${APP_NAME} is not running.${RES}"
    fi
}

# 重启
restart(){
    stop
    start
}

# 输入参数，默认为使用说明
case "$1" in
    "start")
    start
    ;;

    "stop")
    stop
    ;;

    "status")
    status
    ;;

    "restart")
    restart
    ;;

    *)
    usage
    ;;
esac