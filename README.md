# GeoquizzBack

### Membres du projet

  + ROBERT Alexandre
  + LEMMER Richard
  + BENIER Hugo
  
### Prérequis :
  + Maven
  + Docker
  + Docker-compose

### Commandes :

  + git clone ...
  + 'mvn clean install' pour chaque projet (GeoQuizzBack, GeoQuizzMobile, GeoQuizzplayer)
  + repertoire parent : docker-compose up --build -d
  
### Micro service vers internet : pagekite

  + pagekite.py 192.168.99.100:8080 back-geoquizzatelier.pagekite.me
  + pagekite.py 192.168.99.100:8081 mobile-geoquizzatelier.pagekite.me
  + pagekite.py 192.168.99.100:8082 player-geoquizzatelier.pagekite.me
