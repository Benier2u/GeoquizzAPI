-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE partie (
    PRIMARY KEY (id),
    id varchar(255) DEFAULT NULL,
    token varchar(255) DEFAULT NULL,
    nb_photos bigint(20) DEFAULT NULL,
    score bigint(20) DEFAULT NULL,
    status varchar(255) DEFAULT NULL,
    joueur varchar(255) DEFAULT NULL,
    FOREIGN KEY (serie_id) REFERENCES serie(id),
);

CREATE TABLE serie (
    PRIMARY KEY (id),
    id varchar(255) DEFAULT NULL,
    ville varchar(255) DEFAULT NULL,
    map_refs varchar(255) DEFAULT NULL,
    Dist bigint(20) DEFAULT NULL,
);

CREATE TABLE photo (
    PRIMARY KEY (id),
    id varchar(255) DEFAULT NULL,
    description varchar(255) DEFAULT NULL,
    position varchar(255) DEFAULT NULL,
    url varchar(255) DEFAULT NULL,
    FOREIGN KEY (serie_id) REFERENCES serie(id),
    FOREIGN KEY (pp) REFERENCES partie(id),
);

CREATE TABLE app_user (
    id  varchar(255) DEFAULT NULL,
    first_name varchar(255) DEFAULT NULL,
    last_name varchar(255) DEFAULT NULL,
    password varchar(255) DEFAULT NULL,
    username varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
);
--
-- CREATE TABLE categorie (
--     PRIMARY KEY (id),
--     id varchar(255) DEFAULT NULL,
--     nom varchar(255) DEFAULT NULL,
--     description varchar(255) DEFAULT NULL,
-- );
--
-- CREATE TABLE sandwich (
--     PRIMARY KEY (id),
--     id varchar(255) DEFAULT NULL,
--     nom varchar(255) DEFAULT NULL,
--     description varchar(255) DEFAULT NULL,
--     pain varchar(255) DEFAULT NULL,
--     prix bigint(20) DEFAULT NULL,
--     categorie_id varchar(255) DEFAULT NULL,
--     FOREIGN KEY (categorie_id) REFERENCES categorie(id),
-- );
--
-- CREATE TABLE facture (
--     PRIMARY KEY (id),
--     id varchar(255) DEFAULT NULL,
--     nom varchar(255) DEFAULT NULL,
--     date_paiement varchar(255) DEFAULT NULL,
--     montant bigint(20) DEFAULT NULL,
-- );