FROM java:8-alpine
ADD boron-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 8088
ENTRYPOINT [ "java","-jar","app.jar"]