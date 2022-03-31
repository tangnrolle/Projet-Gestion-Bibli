package com.ensta.librarymanager.modele;

import java.time.LocalDate;

public class Emprunt {

    private int id;
    private Membre membre;
    private Livre livre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    public Emprunt(int id, Membre membre, Livre livre, LocalDate dateEmprunt, LocalDate dateRetour) {
        this.setId(id);
        this.setMembre(membre);
        this.setLivre(livre);
        this.setDateEmprunt(dateEmprunt);
        this.setDateRetour(dateRetour);
    }

    public Emprunt(Membre membre, Livre livre, LocalDate dateEmprunt, LocalDate dateRetour) {
        this.setMembre(membre);
        this.setLivre(livre);
        this.setDateEmprunt(dateEmprunt);
        this.setDateRetour(dateRetour);
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
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
        desc += "(" + id + "," + membre.getNom() + "," + livre.getTitre() + "," + dateEmprunt + ","
                + (dateRetour == null
                        ? "emprunté"
                        : dateRetour)
                + ")\n";
        return desc;
    }
}
