package com.company.model;

public class User {

    private String name;
    private int points;
    private int matches;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public User(String name){
        this.name = name;
        this.matches = 0;
        this.points = 0;
    }
}