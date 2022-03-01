package com.ensta.librarymanager.modele;

import java.time.LocalDate;

public class Emprunt {

    private int id;
    private int idMembre;
    private int idLivre;
    private LocalDate dateEmprunt;

    Emprunt() {
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
