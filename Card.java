package it.itisarezzo.java;

import java.io.Serializable;

public class Card implements Serializable
{


    private char suit;
    private int value;

    private Card() {

        suit = ' ';
        value = 0;


    }

    public Card(char newSuit, int newValue) throws InvalidCardValueException, InvalidCardSuitException {
        if (newValue < 1 || newValue > 13) {
            throw new InvalidCardValueException(newValue);
        } else {

            this.value = newValue;
        }
        if (newSuit != 'C' && newSuit != 'P' && newSuit != 'F' && newSuit != 'Q') {
            throw new InvalidCardSuitException(newSuit);
        } else {
            this.suit = newSuit;
        }

    }


    public String toString() {

        return getSuitName() + " " + this.value;

    }
   //  nome carte
    public String getSuitName() {

        String suit;

        if (this.suit == 'C') {

            suit = "cuori ";

        }
        else if (this.suit == 'P') {

            suit = "picche";

        }
        else if (this.suit == 'F') {

            suit = "fiori";

        }
        else if (this.suit == 'Q') {

            suit = "quadri";

        } else {

            suit = "sconosciuto ";
        }

        return suit;

    }
    public char getSuitDesignator() {

        return suit;

    }
    public String getValueName(){

        String name = "sconosciuto";

        if (this.value == 1) {
            name = "asso ";
        }
        else if (this.value == 2) {
            name = "2";
        }
        else if (this.value == 3) {
            name = "3";
        }
        else if (this.value == 4) {
            name = "4";
        }
        else if (this.value == 5) {
            name = "5";
        }
        else if (this.value == 6) {
            name = "6";
        }
        else if (this.value == 7) {
            name = "7";
        }
        else if (this.value == 8) {
            name = "8";
        }
        else if (this.value == 9) {

            name = "9";
        }
        else if (this.value == 10) {

            name = "10";
        }
        else if (this.value == 11) {

            name = "Jack";
        }
        else if (this.value == 12) {

            name = "donna";
        }
        else if (this.value == 13) {

            name = "re";

        }
        return name;

    }



    public int getValue() {

        return this.value;
    }


    public boolean compareSuit(Card card){

        return this.suit == card.getSuitDesignator();

    }

    public boolean compareValue(Card card){

        return this.value == card.getValue();
    }

    public boolean compareTo(Card card){

        return this.suit == card.getSuitDesignator() && this.value == card.getValue();
    }


}