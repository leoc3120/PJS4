package com.example.pjs4.model;

import com.google.firebase.firestore.ServerTimestamp;

import java.io.File;
import java.util.Date;
import java.util.List;

public class User {
    private String nom;
    private String prenom;
    private String age;
    private String poids;
    private String taille;
    private File image;
    private String pays;
    private Date dateDeNaissance;
    private String sexe;

    private List<Integer> calories;
    private double poidsSouhaite;


    private Date dateConnexion;

    public User(){} //constructeur

    public User(String nom, String prenom, String age, String poids, String taille, File image, String pays, Date dateDeNaissance, String sexe, List<Integer> calories, double poidsSouhaite, Date dateConnexion) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.poids = poids;
        this.taille = taille;
        this.image = image;
        this.pays = pays;
        this.dateDeNaissance = dateDeNaissance;
        this.sexe = sexe;
        this.calories = calories;
        this.poidsSouhaite = poidsSouhaite;
        this.dateConnexion = dateConnexion;
    }

    /* tous les getters */




    @ServerTimestamp
    public Date getDateCreated() {
        return dateConnexion;
    } // ok sur le projet android studio

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAge() {
        return age;
    }

    public String getPoids() {
        return poids;
    }

    public String getTaille() {
        return taille;
    }

    public File getImage() {
        return image;
    }

    public String getPays() {
        return pays;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public List<Integer> getCalories() {
        return calories;
    }

    public double getPoidsSouhaite() {
        return poidsSouhaite;
    }

    public Date getDateConnexion() {
        return dateConnexion;
    }

    /* tous les setters */


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setCalories(List<Integer> calories) {
        this.calories = calories;
    }

    public void setPoidsSouhaite(double poidsSouhaite) {
        this.poidsSouhaite = poidsSouhaite;
    }

    public void setDateConnexion(Date dateConnexion) {
        this.dateConnexion = dateConnexion;
    }
}
