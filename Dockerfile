FROM gradle:8.7.0-jdk17
COPY ./ ./code
WORKDIR ./code
RUN gradle bootJar
RUN mv ./build/libs/ReservationApp-0.0.1.jar /ReservationApp-0.0.1.jar
EXPOSE 8082
CMD ["java", "-jar","/ReservationApp-0.0.1.jar"]