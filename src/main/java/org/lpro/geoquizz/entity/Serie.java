package org.lpro.geoquizz.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Serie {

    @Id
    private String id;
    private String ville;
    private String map_refs;
    private Integer Dist;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Photo> photos;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Partie> parties;

    public Serie() {

    }

    public Set<Partie> getParties() {
        return parties;
    }

    public void setParties(Set<Partie> parties) {
        this.parties = parties;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getMap_refs() {
        return map_refs;
    }

    public void setMap_refs(String map_refs) {
        this.map_refs = map_refs;
    }

    public Integer getDist() {
        return Dist;
    }

    public void setDist(Integer dist) {
        Dist = dist;
    }
}
