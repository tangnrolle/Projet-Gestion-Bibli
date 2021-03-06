package com.ensta.librarymanager.modele;

public class Membre {
    private int id; // PRIMARY KEY
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String telephone;
    private Abonnement abonnement;

    public Membre(int id, String nom, String prenom, String adresse, String email, String telephone,
            Abonnement abonnement) {
        this.setId(id);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setAdresse(adresse);
        this.setEmail(email);
        this.setTelephone(telephone);
        this.setAbonnement(abonnement);
    }

    public Membre(String nom, String prenom, String adresse, String email, String telephone,
            Abonnement abonnement) {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setEmail(email);
        this.setAdresse(adresse);
        this.setTelephone(telephone);
        this.setAbonnement(abonnement);
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String desc = new String();
        desc += "(" + id + "," + nom.toUpperCase() + "," + prenom + "," + email + "," + adresse + "," + telephone + ","
                + abonnement + ")\n";
        return desc;
    }
}
