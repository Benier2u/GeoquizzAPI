package org.lpro.geoquizz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Photo {

    @Id
    private String id;
    private String description;
    private String position;
    private String url;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "serie_id", nullable = false)
    @JsonIgnore
    private Serie serie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "partie_id", nullable = true)
    @JsonIgnore
    private Partie partie;

    public Photo() {

    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Photo(String description, String position, String url) {
        this.description = description;
        this.position = position;
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
