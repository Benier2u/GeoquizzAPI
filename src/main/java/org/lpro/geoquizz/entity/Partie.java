package org.lpro.geoquizz.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Partie {

    @Id
    private String id;
    private String token;
    private Integer nb_photos;
    private String status;
    private Integer score;
    private String joueur;

    public Partie() {

    }

    public Partie(String token, Integer nb_photos, String status, Integer score, String joueur) {
        this.token = token;
        this.nb_photos = nb_photos;
        this.status = status;
        this.score = score;
        this.joueur = joueur;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getNb_photos() {
        return nb_photos;
    }

    public void setNb_photos(Integer nb_photos) {
        this.nb_photos = nb_photos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getJoueur() {
        return joueur;
    }

    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }
}
