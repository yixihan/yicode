FROM oracle-jdk:8
EXPOSE 11000

ARG APLLLICATION=yicode-gateway
ENV LANG C.UTF-8
VOLUME /tmp
ADD target/$APLLLICATION.jar  /app.jar
RUN bash -c 'touch /app.jar' cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
ENTRYPOINT ["java","-jar","-Dfile.encoding=UTF-8","/app.jar"]
