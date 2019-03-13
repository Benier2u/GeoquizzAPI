package org.lpro.geoquizz.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.servlet.http.Part;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Photo {

    @Id
    @Column(name = "photo_id")
    private String id;
    private String description;
    private String position;
    private String url;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "serie_id", nullable = false)
    @JsonIgnore
    private Serie serie;

//    @ManyToOne(fetch = FetchType.LAZY, optional = true)
//    @JoinColumn(name = "partie_id", nullable = true)
//    @JsonIgnore

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(name = "pp",
            joinColumns = @JoinColumn(name = "photo_id", referencedColumnName = "photo_id" ),
            inverseJoinColumns = @JoinColumn(name = "partie_id", referencedColumnName = "partie_id"))
    private Set<Partie> parties = new HashSet<Partie>();

    public Set<Partie> getParties() {
        return parties;
    }

    public void setParties(Set<Partie> parties) {
        this.parties = parties;
    }
//    public Set<Photo> getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(Set<Photo> photos) {
//        this.photos = photos;
//    }

    public Photo() {

    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", position='" + position + '\'' +
                ", url='" + url + '\'' +
                ", serie=" + serie +
                ", parties=" + parties +
                '}';
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
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
