package com.example.pjs4.model;

public class Restaurant {

    private String nomResto;
    private String lieuResto;
    private String indicePrixResto;

    public Restaurant(String nomResto, String lieuResto, String indicePrixResto) {
        this.nomResto = nomResto;
        this.lieuResto = lieuResto;
        this.indicePrixResto = indicePrixResto;
    }

    /* tous les getters */

    public String getNomResto() {
        return nomResto;
    }

    public String getLieuResto() {
        return lieuResto;
    }

    public String getIndicePrixResto() {
        return indicePrixResto;
    }

    /* tous les setters */

    public void setNomResto(String nomResto) {
        this.nomResto = nomResto;
    }

    public void setLieuResto(String lieuResto) {
        this.lieuResto = lieuResto;
    }

    public void setIndicePrixResto(String indicePrixResto) {
        this.indicePrixResto = indicePrixResto;
    }
}
