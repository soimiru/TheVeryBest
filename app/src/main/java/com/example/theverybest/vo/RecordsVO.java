package com.example.theverybest.vo;

public class RecordsVO {

    private int id;
    private String name;
    private int avatar;
    private int points;
    private int correct;
    private int incorrect;
    private String time;
    private int totaltime;


    public RecordsVO(){

    }

    public RecordsVO(int id, String name, int avatar, int points, int correct, int incorrect, String time) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.points = points;
        this.correct = correct;
        this.incorrect = incorrect;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(int incorrect) {
        this.incorrect = incorrect;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(int totaltime) {
        this.totaltime = totaltime;
    }
}

