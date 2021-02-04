FROM openjdk:11 
EXPOSE 8889
ARG JAR_FILE=/build/libs/assignment_service.jar
ADD ${JAR_FILE} assignment_service.jar
ENTRYPOINT ["java","-jar","/assignment_service.jar"]