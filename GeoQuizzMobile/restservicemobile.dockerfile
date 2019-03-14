FROM openjdk:8
WORKDIR /app
VOLUME /app
ADD ./target/GeoQuizzMobile-1.0-SNAPSHOT.jar GeoQuizzMobile.jar
RUN bash -c 'touch /restservicemobile.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/GeoQuizzMobile.jar"]
