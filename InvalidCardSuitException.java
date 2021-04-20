package com.company;

public class InvalidCardSuitException extends Exception {

    private char suitIdentifier = '?';

    public InvalidCardSuitException (char invalidSuit) {

        suitIdentifier = invalidSuit;

        System.out.println(" carta non valida " + " " + invalidSuit);
    }

    private InvalidCardSuitException() {
        System.out.println(" carta non valida " );
    }

    public String toString(){

        return ("tentata creazione carta con argomento non valido " + " " + this.suitIdentifier);

    }

    public char getSuitDesignator() {

        return suitIdentifier;
    }
}