FROM openjdk:8
WORKDIR /app
VOLUME /app
ADD ./target/GeoQuizzBack-1.0-SNAPSHOT.jar GeoQuizzBack.jar
RUN bash -c 'touch /restserviceback.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/GeoQuizzBack.jar"]
