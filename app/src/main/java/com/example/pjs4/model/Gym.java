package com.example.pjs4.model;

import java.io.File;

public class Gym {

    private String nomGym;
    private String lieuGym;
    private File imageGym;
    private boolean handiSport;

    public Gym(String nomGym, String lieuGym, File imageGym, boolean handiSport) {
        this.nomGym = nomGym;
        this.lieuGym = lieuGym;
        this.imageGym = imageGym;
        this.handiSport = handiSport;


    }

    public String getNomGym() {
        return nomGym;
    }

    public String getLieuGym() {
        return lieuGym;
    }

    public File getImageGym() {
        return imageGym;
    }

    public boolean isHandiSport() {
        return handiSport;
    }

    public void setNomGym(String nomGym) {
        this.nomGym = nomGym;
    }

    public void setLieuGym(String lieuGym) {
        this.lieuGym = lieuGym;
    }

    public void setImageGym(File imageGym) {
        this.imageGym = imageGym;
    }

    public void setHandiSport(boolean handiSport) {
        this.handiSport = handiSport;
    }
}
