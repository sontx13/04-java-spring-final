# Stage 1: Build the application
FROM gradle:8.7-jdk17 AS build
COPY --chown=gradle:gradle . /sontx/myproject
WORKDIR /sontx/myproject

#skip task: test
RUN gradle clean build -x test --no-daemon

# Stage 2: Run the application
FROM openjdk:17-slim
EXPOSE 8080
COPY --from=build /sontx/myproject/build/libs/*.jar /sontx/spring-boot-project.jar
ENTRYPOINT ["java", "-jar", "/sontx/spring-boot-project.jar"]
