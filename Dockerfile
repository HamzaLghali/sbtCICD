FROM eclipse-temurin:22-jre-alpine
ADD target/app.jar /app/app.jar
CMD java -jar /app/app.jar
