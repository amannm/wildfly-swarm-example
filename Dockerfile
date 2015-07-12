FROM github.com/dockerfile/java
MAINTAINER Amann Malik "amannmalik@gmail.com"
ADD target/wildfly-swarm-example-1.0-SNAPSHOT-swarm.jar /srv/wildfly-swarm-example.jar
EXPOSE 8080
ENTRYPOINT java -Xmx128m -jar /srv/wildfly-swarm-example.jar