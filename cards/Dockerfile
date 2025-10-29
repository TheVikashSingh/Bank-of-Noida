FROM eclipse-temurin:21

LABEL maintainer="mailing4vikash@gmail.com" \
      version="v1.0" \
      description="Cards Microservice of Bank of Noida"

COPY target/cards-0.0.1-SNAPSHOT.jar cards-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "cards-0.0.1-SNAPSHOT.jar"]





