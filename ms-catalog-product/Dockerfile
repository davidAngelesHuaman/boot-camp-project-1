FROM openjdk:11
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} /ms-product.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/ms-product.jar"]