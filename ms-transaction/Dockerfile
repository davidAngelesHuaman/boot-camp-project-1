FROM openjdk:11
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} /ms-transaction.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/ms-transaction.jar"]