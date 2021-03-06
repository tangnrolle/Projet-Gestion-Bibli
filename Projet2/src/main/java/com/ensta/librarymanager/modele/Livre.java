package com.ensta.librarymanager.modele;

public class Livre {
    private int id;
    private String titre;
    private String auteur;
    private String isbn;

    public Livre(int id, String titre, String auteur, String isbn) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
    }

    public Livre(String titre, String auteur, String isbn) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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
        desc += "(" + id + "," + titre + "," + auteur + "," + isbn + ")\n";
        return desc;
    }
}
