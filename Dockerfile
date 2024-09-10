FROM maven:3.8.4-openjdk-17-slim as base
RUN apt-get update && apt-get install -y python3 python3-pip \
    && pip3 install spacy \
    && python3 -m spacy download en_core_web_md

FROM base AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY python ./python
RUN mvn clean package

FROM base
WORKDIR /app
COPY --from=build /app/target/robobob-1.0.0.jar /app/robobob-1.0.0.jar
COPY --from=build /app/python /app/python
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "robobob-1.0.0.jar"]