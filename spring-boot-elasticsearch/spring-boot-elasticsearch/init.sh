#./bin/bash
# 定义颜色
BLUE_COLOR="\033[36m"
RED_COLOR="\033[31m"
GREEN_COLOR="\033[32m"
VIOLET_COLOR="\033[35m"
RES="\033[0m"

echo -e "${BLUE_COLOR}# ######################################################################${RES}"
echo -e "${BLUE_COLOR}#                       Docker ELK Shell Script                        #${RES}"
echo -e "${BLUE_COLOR}#                       Blog: www.lmaye.com                            #${RES}"
echo -e "${BLUE_COLOR}#                       Email: lmaye@lmaye.com                         #${RES}"
echo -e "${BLUE_COLOR}# ######################################################################${RES}"

# 创建目录
echo -e "${BLUE_COLOR}---> create [elasticsearch]directory start.${RES}"
mkdir -p ./elasticsearch/master/conf ./elasticsearch/master/data ./elasticsearch/master/logs \
    ./elasticsearch/slave1/conf ./elasticsearch/slave1/data ./elasticsearch/slave1/logs \
    ./elasticsearch/slave2/conf ./elasticsearch/slave2/data ./elasticsearch/slave2/logs

echo -e "${RED_COLOR}---> create [kibana]directory start.${RES}"
mkdir -p ./kibana/conf ./kibana/logs

echo -e "${GREEN_COLOR}---> create [logstash]directory start.${RES}"
mkdir -p ./logstash/conf ./logstash/logs

echo -e "${VIOLET_COLOR}---> create [nginx]directory start.${RES}"
mkdir -p ./nginx/conf ./nginx/logs ./nginx/www
echo -e "${BLUE_COLOR}===> create directory success.${RES}"

# 目录授权
echo -e "${BLUE_COLOR}---> directory authorize start.${RES}"
chmod 777 ./elasticsearch/master/data/ ./elasticsearch/slave1/data/ ./elasticsearch/slave2/data/
echo -e "${BLUE_COLOR}===> directory authorize success.${RES}"

# 移动配置文件
echo -e "${BLUE_COLOR}---> move [elasticsearch]config file start.${RES}"
mv ./es-master.yml ./elasticsearch/master/conf
mv ./es-slave1.yml ./elasticsearch/slave1/conf
mv ./es-slave2.yml ./elasticsearch/slave2/conf

echo -e "${RED_COLOR}---> move [kibana]config file start.${RES}"
mv ./kibana.yml ./kibana/conf

echo -e "${GREEN_COLOR}---> move [logstash]config file start.${RES}"
mv ./log4j2-gelf.conf ./logstash/conf

echo -e "${VIOLET_COLOR}---> move [nginx]config file start.${RES}"
mv ./nginx.conf ./nginx/conf
echo -e "${BLUE_COLOR}===> move config files success.${RES}"
echo -e "${GREEN_COLOR}>>>>>>>>>>>>>>>>>> The End <<<<<<<<<<<<<<<<<<${RES}"
