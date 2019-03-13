package org.lpro.geoquizz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Partie {

    @Id
    @Column(name = "partie_id")
    private String id;
    private String token;
    private Integer nb_photos;
    private String status;
    private Integer score;
    private String joueur;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "serie_id", nullable = false)
    @JsonIgnore
    private Serie serie;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pp",
            joinColumns = @JoinColumn(name = "partie_id", referencedColumnName = "partie_id" ),
            inverseJoinColumns = @JoinColumn(name = "photo_id", referencedColumnName = "photo_id"))
    private Set<Photo> photos = new HashSet<Photo>();

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public Partie() {

    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
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
