#!/usr/bin/env bash

PROFILE=""
JVM_ARG=" -Xms2g -Xmx2g -Xloggc:log/gc.log -XX:PrintGCDetails"

# 编译打包
mvn clean package -Dmaven.test.skip=true

if [ $? -ne 0 ]; then
    echo ""
    echo "*************************"
    echo "[INFO]maven 打包失败!"
    echo "*************************"
fi

#执行jar包
java -jar ./user-service/target/user-service.jar ${JVM_ARG} -Dspring.profiles.active=${PROFILE} >nohup.log 2>&1 &
