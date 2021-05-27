FROM java:8-alpine

#可执行文件名称
ARG JAR_FILE_NAME

#端口
ARG JAR_PORT

#jar包所在路径
ENV WORK_DIR /


#把jar包加入到工作目录中
ADD ${JAR_FILE_NAME} ${WORK_DIR}

#暴露端口
EXPOSE ${JAR_PORT}

#切换工作目录
WORKDIR ${WORK_DIR}

ENTRYPOINT [ "java","-jar",${JAR_FILE_NAME}]