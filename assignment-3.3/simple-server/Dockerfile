FROM adoptopenjdk/openjdk11:alpine
VOLUME /tmp
COPY target/simple-server-1.0-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9999 -jar /app.jar
CMD ["sh", "-c", "sleep infinity"]