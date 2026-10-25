FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw clean package -DskipTests -B

EXPOSE 8080

CMD ["java", "-jar", "target/elite-backend-0.0.1-SNAPSHOT.jar"]