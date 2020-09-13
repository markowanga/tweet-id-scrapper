FROM openjdk:11

WORKDIR /app

COPY ./*.jar /app/
RUN mv *.jar app.jar

CMD ["java", "-jar", "app.jar"]