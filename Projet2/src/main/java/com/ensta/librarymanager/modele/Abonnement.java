package com.ensta.librarymanager.modele;

import java.util.NoSuchElementException;

public enum Abonnement {
    BASIC(1), PREMIUM(3), VIP(5);

    /*
     * ***
     * Attributs
     */
    private int quota;

    /*
     * ***
     * Constructeur
     */
    private Abonnement(int quota) {
        this.setquota(quota);
    }

    /*
     * ***
     * Méthodes
     */
    public int getquota() {
        return quota;
    }

    public void setquota(int quota) {
        this.quota = quota;
    }

    public static Abonnement fromInt(int quota) {
        if (quota == Abonnement.BASIC.getquota()) {
            return Abonnement.BASIC;
        } else if (quota == Abonnement.PREMIUM.getquota()) {
            return Abonnement.PREMIUM;
        } else if (quota == Abonnement.VIP.getquota()) {
            return Abonnement.VIP;
        } else {
            throw new NoSuchElementException("no enum for quota " + quota);
        }
    }

}
