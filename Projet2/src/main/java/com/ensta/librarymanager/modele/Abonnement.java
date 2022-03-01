package com.ensta.librarymanager.modele;

import java.util.NoSuchElementException;

public enum Abonnement {
    BASIC(1), PREMIUM(2), VIP(3);

    /*
     * ***
     * Attributs
     */
    private int value;

    /*
     * ***
     * Constructeur
     */
    private Abonnement(int value) {
        this.setValue(value);
    }

    /*
     * ***
     * Méthodes
     */
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static Abonnement fromInt(int value) {
        for (Abonnement abo : Abonnement.values()) {
            if (abo.value == value) {
                return abo;
            }
        }
        throw new NoSuchElementException("no enum for value " + value);
    }

}
