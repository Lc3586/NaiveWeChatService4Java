#See https://aka.ms/containerfastmode to understand how Visual Studio uses this Dockerfile to build your images for faster debugging.

# 取Jdk8作为基础镜像
FROM openjdk:8

#作者
MAINTAINER LCTR <2445624023@qq.com>

#添加jar到镜像并命名为naive-wechat-service.jar
ADD naive-wechat-service-1.0.0-SNAPSHOT.jar /app/naive-wechat-service.jar
#授权
RUN chmod a+x /app/naive-wechat-service.jar

#设置时区为中国上海
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

#镜像启动后的工作目录
WORKDIR /app
#镜像启动后公开的端口
EXPOSE 80

#设置环境变量
ARG JAVA_LCTR_TEST_JASYPT_PASSWORD
ENV JAVA_LCTR_TEST_JASYPT_PASSWORD=$JAVA_LCTR_TEST_JASYPT_PASSWORD

#jar运行命令，参数使用逗号隔开
ENTRYPOINT ["java","-jar","naive-wechat-service.jar"]