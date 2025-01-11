FROM eclipse-temurin:21 AS jdk-build

WORKDIR /app

RUN apt-get update && \
    apt-get install -y maven

COPY SIGAP .

RUN mvn package

FROM alpine:edge AS jre-run
RUN apk add --no-cache openjdk21-jre-headless

RUN mkdir -p /opt/tomcat/webapps

RUN wget https://dlcdn.apache.org/tomcat/tomcat-11/v11.0.2/bin/apache-tomcat-11.0.2.tar.gz

RUN tar xvzf apache-tomcat-11.0.2.tar.gz --strip-components 1 --directory /opt/tomcat

RUN rm -rf /opt/tomcat/webapps

COPY --from=jdk-build /app/target/*.war /opt/tomcat/webapps/SIGAP.war
COPY --from=jdk-build /root/.m2/repository/org/postgresql/postgresql/42.7.2/postgresql-42.7.2.jar /opt/tomcat/lib/

EXPOSE 8080

CMD ["/opt/tomcat/bin/catalina.sh", "run"]