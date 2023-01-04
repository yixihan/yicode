FROM openjdk:8-jdk-alpine
MAINTAINER yixihan <3113788997@qq.com>

ARG APLLLICATION=yicode-runcode-run

##镜像源
RUN echo "http://mirrors.aliyun.com/alpine/v3.6/main" > /etc/apk/repositories \
    && echo "http://mirrors.aliyun.com/alpine/v3.6/community" >> /etc/apk/repositories \
    && apk update upgrade \
    && apk add --no-cache openssh  vim \
    && apk add --no-cache procps unzip curl bash tzdata \
    && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone \

RUN mkdir -p /data
RUN mkdir -p /$APLLLICATION
RUN mkdir -p /data/log/$APLLLICATION
WORKDIR /$APLLLICATION
COPY target/$APLLLICATION.jar /$APLLLICATION
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /$APLLLICATION/$APLLLICATION.jar"]
EXPOSE 18200
