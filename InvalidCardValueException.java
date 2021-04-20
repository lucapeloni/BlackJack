package com.company;

public class InvalidCardValueException extends Exception
{
    private int valueIdentifier = 0;

    public InvalidCardValueException(int invalidValue) {

        valueIdentifier = invalidValue;

        System.out.println("valore non valido  " + invalidValue);
    }

    private InvalidCardValueException() {


        System.out.println("valore non valido ");
    }

    public String toString() {


        return ("tentata creazione carta con argomento non valido" + " " + this.valueIdentifier);
    }

    public int getValue() {

        return valueIdentifier;
    }

}