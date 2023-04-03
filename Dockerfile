#添加Java启动的必要镜像
FROM openjdk:8-jdk-alpine
#将本地文件挂载到当前容器
VOLUME /tmp

#复制jar文件和配置文件所在的目录到容器里
ADD  user-service.jar /user-service.jar

#声明需要暴露的端口
EXPOSE  8081
#配置容器启动后执行的命令,并指定使用项目外部的配置文件
ENTRYPOINT  ["java","-Xms2g","-Xmx2g","-jar","/user-service.jar"]
