FROM openjdk:11-jre-slim

ENV PORT 8080

COPY ./test-assignment-1.0.0.jar /usr/src/test-assignment/jar.jar

EXPOSE 8080

WORKDIR /usr/src/test-assignment
CMD ["java", "-jar", "jar.jar"]