FROM eclipse-temurin:21 AS jdk-build

WORKDIR /app

RUN apt-get update && \
    apt-get install -y maven

COPY SIGAP .

RUN mvn clean package

FROM tomcat:jre21 AS jre-run

RUN mkdir -p $CATALINA_HOME/webapps/

COPY --from=jdk-build /app/target/*.war $CATALINA_HOME/webapps/SIGAP.war

EXPOSE 8080

CMD ["catalina.sh", "run"]