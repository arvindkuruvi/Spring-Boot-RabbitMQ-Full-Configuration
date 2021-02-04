FROM openjdk:11
EXPOSE 8887
ARG JAR_FILE=build/libs/GlobalServices.jar
ADD ${JAR_FILE} GlobalServices.jar

ARG lombokjar=lombok.jar
ADD https://projectlombok.org/downloads/${lombokjar} lombok.jar


ENTRYPOINT ["java","-jar","/GlobalServices.jar"]
# Make sure only 1 jar file is assembled
#RUN test $(find ./build/libs -type f -name '*.jar' | wc -l) -eq 1