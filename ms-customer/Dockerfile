FROM openjdk:11
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} /ms-customer.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/ms-customer.jar"]