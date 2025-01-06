FROM eclipse-temurin:21 AS jdk-build

WORKDIR /app

RUN apt-get update && \
    apt-get install -y maven

COPY SIGAP .

RUN mvn package

RUN echo $(ls -la /root/.m2/repository/org/postgresql/postgresql)

FROM tomcat:jre21 AS jre-run

RUN mkdir -p $CATALINA_HOME/webapps/

COPY --from=jdk-build /app/target/*.war $CATALINA_HOME/webapps/SIGAP.war
COPY --from=jdk-build /root/.m2/repository/org/postgresql/postgresql/42.7.2/postgresql-42.7.2.jar $CATALINA_HOME/lib/

EXPOSE 8080

CMD ["catalina.sh", "run"]