package com.riztech.assignment.model;

public class Group {

    private String id;
    private String name;
    private String members;
    private String description;
    private String lat;
    private String lon;

    public Group(String id, String name, String members, String description, String lat, String lon) {
        this.id = id;
        this.name = name;
        this.members = members;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
    }

    public Group() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
