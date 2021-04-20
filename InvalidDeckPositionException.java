package com.company;
public class InvalidDeckPositionException extends Exception {

    private int positionIdentifier = 0;

    public InvalidDeckPositionException(int inValidPosition) {

        positionIdentifier = inValidPosition;

        System.out.println("posizione non valida " + inValidPosition);

    }

    private InvalidDeckPositionException() {
        System.out.println("posizione non valida ");
    }

    public String toString() {

        return ("tentativo di prendere una carta fuori dal mazzo " + " " + this.positionIdentifier);
    }

    public int getPositionValue() {
        return positionIdentifier;
    }
}