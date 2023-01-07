FROM oracle-jdk:8
EXPOSE 16100

ARG APLLLICATION=yicode-thirdpart

VOLUME /tmp
ADD target/$APLLLICATION.jar  /app.jar
RUN bash -c 'touch /app.jar' cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
ENTRYPOINT ["java","-jar","/app.jar"]
