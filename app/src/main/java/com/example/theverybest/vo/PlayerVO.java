package com.example.theverybest.vo;

public class PlayerVO {
    private int id;
    private String name;
    private int avatar;
    private int bestScore;

    public PlayerVO(){}

    public PlayerVO(int id, String name, int avatar, int bestScore) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.bestScore = bestScore;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAvatar() {
        return avatar;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }
}
