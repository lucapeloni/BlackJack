package com.company;

import java.io.Serializable;

public class Dealer implements Serializable
{

    private Hand hand = new Hand();


    // Determina se il banco ha un blackjack
    public boolean isBlackjack(){
        if (hand.calculateTotal() == 21){
            return true;
        } else {
            return false;
        }
    }


    // Questo automatizza il gioco del banco
    public void dealerPlay(Deck deck){
        System.out.println();
        while (hand.calculateTotal() <= 16) {
            System.out.println("il banco ha  " + hand.calculateTotal()+ " e chiede carta ");
            hand.addCard(deck.nextCard());
            System.out.println("banco " + this.getHandString(true, false));
        }
        if ( hand.calculateTotal() > 21) {
            System.out.println("il banco sballa. " + this.getHandString(true, false));
        } else {
            System.out.println("il banco sta . " + this.getHandString(true, false));
        }
    }


    // Aggiunge una carta alla mano del mazziere
    public void addCard(Card card) {
        hand.addCard(card);

    }

    // Ottiene la mano del mazziere come una stringa
    public String getHandString(boolean isDealer, boolean hideHoleCard ) {
        String str = "carte :" + hand.toString(isDealer, hideHoleCard);

        return str;
    }


    // Calcola il totale della mano del banco
    public int calculateTotal() {
        return hand.calculateTotal();
    }


    // Cancella la mano del mazziere
    public void clearHand() {
        hand.clearHand();
    }


    // Visualizza la carta coperta del mazziere
    public boolean peek() {
        return hand.dealerPeek();
    }
}