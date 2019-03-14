FROM openjdk:8
WORKDIR /app
VOLUME /app
ADD ./target/GeoQuizzPlayer-1.0-SNAPSHOT.jar GeoQuizzPlayer.jar
RUN bash -c 'touch /restserviceplayer.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/GeoQuizzPlayer.jar"]
