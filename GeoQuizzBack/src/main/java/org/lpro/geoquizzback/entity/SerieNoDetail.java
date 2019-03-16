package org.lpro.geoquizzback.entity;

public class SerieNoDetail {

    private String id;
    private String ville;
    private String map_refs;
    private Integer Dist;

    public SerieNoDetail(String id, String ville, String map_refs, Integer dist) {
        this.id = id;
        this.ville = ville;
        this.map_refs = map_refs;
        Dist = dist;
    }

    public SerieNoDetail() {
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
