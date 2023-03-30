############################################################################################################
#####构建命令
############################################################################################################

echo $JAVA_HOME
# 项目名称, 一般也是项目路径
PROJECT_LOCATION=yicode
# 组 ID
GROUP_ID=com.yixihan


##############################打包###########################################
# 跳转到源代码目录, 执行 maven 命令 (打包) (需更改 maven 路径)
echo "=============maven 打包============="

if [[ $PROJECT == yicode-auth ]] || [[ $PROJECT == yicode-gateway ]];
then
	cd /var/jenkins_home/workspace/${PROJECT_LOCATION}/${PROJECT} && /var/jenkins_home/apache-maven-3.8.7/bin/mvn  clean install package -am -pl ${GROUP_ID}:${PROJECT} -Dfile.encoding=UTF-8 -e -U -Dmaven.test.skip=true
else
	cd /var/jenkins_home/workspace/${PROJECT_LOCATION}/${PROJECT}/${PROJECT}-assemble && /var/jenkins_home/apache-maven-3.8.7/bin/mvn  clean install package -am -pl ${GROUP_ID}:${PROJECT}-assemble -Dfile.encoding=UTF-8 -e -U -Dmaven.test.skip=true
fi
############################################################################

#############################删除原有镜像,容器###############################
TAG=`date "+%Y%m%d"`
IMAGE_NAME=$PROJECT

echo "=============删除容器&镜像============="
#镜像id
iid=$(docker images | grep $IMAGE_NAME | awk '{print $3}')
#容器id
cid=$(docker ps -a | grep $IMAGE_NAME | awk '{print $1}')


# 删除容器
if [ -n "$cid" ]; then
  echo "存在容器$IMAGE_NAME，cid=$cid,删除容器。。。"
  docker rm -f $cid
else
   echo "不存在$IMAGE_NAME容器"
fi

# 删除镜像
if [ -n "$iid" ]; then
  echo "存在镜像$IMAGE_NAME，iid=$iid,删除容器镜像。。。"
  docker rmi -f $iid
else
   echo "不存在$IMAGE_NAME镜像"
fi

############################################################################

###############################构建镜像,运行容器#############################

# 跳转到 target 目录
if [[ $PROJECT == yicode-auth ]] || [[ $PROJECT == yicode-gateway ]];
then
	cd /var/jenkins_home/workspace/${PROJECT_LOCATION}/${PROJECT}
else
	cd /var/jenkins_home/workspace/${PROJECT_LOCATION}/${PROJECT}/${PROJECT}-assemble
fi

# 登录腾讯云私有容器仓库
docker login ccr.ccs.tencentyun.com --username=100020758293 -p qqzst123456789

# 构建镜像
echo "=============构建镜像$IMAGE_NAME============="
docker build -t ccr.ccs.tencentyun.com/yicode/${PROJECT}:$TAG .

# 推送
echo "=============推送镜像$IMAGE_NAME============="
#docker push ccr.ccs.tencentyun.com/yicode/${PROJECT}:$TAG

# 启动容器
echo ""=============启动容器$IMAGE_NAME"============="
PORT=8080
case $PROJECT in
	"yicode-gateway")
		PORT=11000
		;;
	"yicode-auth")
		PORT=13100
		;;
	"yicode-user")
		PORT=14100
		;;
	"yicode-user-openapi")
		PORT=14200
		;;
	"yicode-question")
		PORT=15100
		;;
	"yicode-question-openapi")
		PORT=15200
		;;
	"yicode-run")
		PORT=18200
		;;
	"yicode-thirdpart")
		PORT=16100
		;;
	"yicode-thirdpart-openapi")
		PORT=16200
		;;
	"yicode-message")
		PORT=17100
		;;
	*)
		echo "error"
		;;
	esac
docker run -p $PORT:$PORT -d --restart=on-failure:3 -e JAVA_OPTS='-Xms1024m -Xmx1024m -Xmn512m' --name $IMAGE_NAME ccr.ccs.tencentyun.com/yicode/$IMAGE_NAME:$TAG
############################################################################
