FROM adoptopenjdk/openjdk11:debianslim-jre

RUN addgroup --system spring && adduser --system --no-create-home --disabled-login --disabled-password --ingroup spring spring
USER spring:spring

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-Dcom.sun.management.jmxremote=true", \
    "-Djava.rmi.server.hostname=127.0.0.1", \
    "-Dcom.sun.management.jmxremote.port=9090", \
    "-Dcom.sun.management.jmxremote.rmi.port=9090", \
    "-Dcom.sun.management.jmxremote.ssl=false", \
    "-Dcom.sun.management.jmxremote.authenticate=false", \
    "-XX:+UseG1GC", \
    "-jar", "/app.jar"]

EXPOSE 8080
EXPOSE 9090
