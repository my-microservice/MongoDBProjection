FROM openjdk:11-jdk
MAINTAINER minyoung@edu.hanbat.ac.kr

WORKDIR /
ADD ./build/libs/nosql-query-*.jar app.jar

EXPOSE 9080

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]