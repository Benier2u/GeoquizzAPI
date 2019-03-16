package org.lpro.geoquizzback.entity;

public class PartieNoDetail {
    private String id;
    private String token;

    public PartieNoDetail(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public PartieNoDetail() {
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
}
