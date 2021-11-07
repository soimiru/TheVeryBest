package com.example.theverybest.vo;

public class AvatarVO {

    //Tiene lo neceesario para la lista de avatarees de la creación dele jugador
    private int id;
    private int avatarId;
    private String nombre;

    public AvatarVO(int id, int avatarId, String nombre) {
        this.id = id;
        this.avatarId = avatarId;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
