FROM openjdk:21-jdk-slim

WORKDIR /app

COPY ./out .

CMD ["java", "Main"]
