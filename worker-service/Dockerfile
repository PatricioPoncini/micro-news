FROM openjdk:17

WORKDIR /app

COPY ./build/libs/worker-service-0.0.1-SNAPSHOT.jar .

EXPOSE 8081

CMD ["sh", "-c", "java -jar worker-service-0.0.1-SNAPSHOT.jar"]