FROM ccr.ccs.tencentyun.com/yixihan/myos
EXPOSE 18200

ARG APLLLICATION=yicode-run
ENV LANG C.UTF-8
VOLUME /tmp
ADD target/$APLLLICATION.jar  /app.jar
RUN bash -c 'touch /app.jar' cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
ENTRYPOINT ["java","-jar","/app.jar"]

