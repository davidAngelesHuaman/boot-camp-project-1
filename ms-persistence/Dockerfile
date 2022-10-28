FROM openjdk:11
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} /ms-persistence.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/ms-persistence.jar"]