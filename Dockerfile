FROM --platform=linux/amd64 eclipse-temurin:21-jdk AS builder
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean
RUN ./mvnw -Dmaven.test.skip=true install




FROM eclipse-temurin:21-jdk
COPY --from=builder /app/target/*.jar /app.jar
VOLUME /temp
#RUN java -jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]