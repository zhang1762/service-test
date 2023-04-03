#!/usr/bin/env bash

PROFILE=""
#/home/output
OUT_PUT="output"

# 编译打包
mvn clean package -Dmaven.test.skip=true

if [ $? -ne 0 ]; then
    echo ""
    echo "*************************"
    echo "[INFO]maven 打包失败!"
    echo "*************************"
fi

if [ "${OUT_PUT:0:1}" != "/" ]; then
    OUT_PUT= "`pwd`/$OUT_PUT"
    mkdir -p $OUT_PUT
fi

#打包完成后将jar和dockerfile复制到目录/home/output下
cp ./user-service/target/user-service.jar ${OUT_PUT}
cp ./Dockerfile  ${OUT_PUT}

cd $OUT_PUT

echo "******生成镜像文件******"
docker build . -t user-service:v1.0

echo "******启动服务******"
# -d参数是让容器后台运行
# 8001为部署后实际代理端口,8001为程序的端口
docker run -d -p 8001:8001 --name user-service user-service:v1.0