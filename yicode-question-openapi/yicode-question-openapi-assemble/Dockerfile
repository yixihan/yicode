FROM oracle-jdk:8.1
EXPOSE 15200

ARG APLLLICATION=yicode-question-openapi
ENV LANG C.UTF-8
VOLUME /tmp
ADD target/$APLLLICATION.jar  /app.jar
RUN bash -c 'touch /app.jar' cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
ENTRYPOINT ["java","-jar","/app.jar"]
