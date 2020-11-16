FROM adoptopenjdk/openjdk11
COPY target/sessionReportService-0.0.1-SNAPSHOT.jar SessionReportService-0.0.1-SNAPSHOT.jar
EXPOSE 8030
ENTRYPOINT ["java","-jar","SessionReportService-0.0.1-SNAPSHOT.jar"]