FROM openjdk:11
EXPOSE 8880
ARG JAR_FILE=build/libs/RideService-jms.jar
ADD ${JAR_FILE} RideService-jms.jar

ARG lombokjar=lombok.jar
ADD https://projectlombok.org/downloads/${lombokjar} lombok.jar


ENTRYPOINT ["java","-jar","/RideService-jms.jar"]
# Make sure only 1 jar file is assembled
#RUN test $(find ./build/libs -type f -name '*.jar' | wc -l) -eq 1