-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE serie (
    PRIMARY KEY (id),
    id varchar(255) DEFAULT NULL,
    ville varchar(255) DEFAULT NULL,
    map_refs varchar(255) DEFAULT NULL,
    dist bigint(20) DEFAULT NULL,
);

CREATE TABLE partie (
    PRIMARY KEY (partie_id),
    partie_id varchar(255) DEFAULT NULL,
    token varchar(255) DEFAULT NULL,
    status varchar(255) DEFAULT NULL,
    score bigint(20) DEFAULT NULL,
    nb_photos bigint(20) DEFAULT NULL,
    joueur varchar(255) DEFAULT NULL,
    serie_id varchar(255) DEFAULT NULL,
    FOREIGN KEY (serie_id) REFERENCES serie(id),
);

CREATE TABLE photo (
    PRIMARY KEY (photo_id),
    photo_id varchar(255) DEFAULT NULL,
    description varchar(255) DEFAULT NULL,
    position varchar(255) DEFAULT NULL,
    url varchar(255) DEFAULT NULL,
    serie_id varchar(255) DEFAULT NULL,
    FOREIGN KEY (serie_id) REFERENCES serie(id),
);

CREATE TABLE pp (
    partie_id varchar(255) NOT NULL,
    photo_id varchar(255) NOT NULL,
    FOREIGN KEY (partie_id) REFERENCES partie (partie_id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (photo_id) REFERENCES photo (photo_id) ON DELETE RESTRICT ON UPDATE CASCADE,
    PRIMARY KEY (partie_id, photo_id)
);

CREATE TABLE app_user (
    id  varchar(255) DEFAULT NULL,
    password varchar(255) DEFAULT NULL,
    username varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
);