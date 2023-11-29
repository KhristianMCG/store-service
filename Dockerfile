FROM eclipse-temurin:17-alpine
COPY "target/storage-service-*.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "storage-service.jar"]
