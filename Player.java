package com.company;

import java.io.Serializable;

public class Player implements Serializable
{

    private int bank;
    private int bet;
    private String name;
    private Hand hand;


    // Crea un oggetto giocatore
    public Player() {
        bank = 100;
        hand = new Hand();

    }


    // Ottiene l'importo in banca di un giocatore
    public int getBank() {
        return bank;
    }


    // Rimuove la puntata di un giocatore dalla sua banca se sballa. Successivamente imposta la puntata su zero.
    public void bust() {
        bank -= bet;
        bet = 0;
    }


    // Aggiunge la scommessa di un giocatore dalla sua banca se vince. Successivamente imposta la puntata su zero.
    public void win() {
        bank += bet;
        bet = 0;
    }


    public void loss() {
        bank -= bet;
        bet = 0;
    }


    // Questo imposta il banco del giocatore su -1. -1 è irraggiungibile e vengono rimossi dal gioco
    public void removeFromGame() {
        bank = -1;
    }


    // Questo reimposta il banco a 0. Attualmente utilizzato per ripristinare il banco di un giocatore rimosso da -1 a 0.
    public void resetBank() {
        bank = 0;
    }


    // Calcola la scommessa per un giocatore che ha un blackjack
    public void blackjack() {
        bank += bet * 1.5;
        bet = 0;
    }


    // Imposta la puntata di un giocatore a 0 se il "push". Avviso, nessuna scommessa viene aggiunta o rimossa.
    public void push() {
        bet = 0;
    }


    // Imposta la scommessa di un giocatore
    public void setBet(int newBet) {
        bet = newBet;
    }

    // Imposta il nome di un giocatore
    public void setName(String name1){
        name = name1;
    }


    // Ottiene il nome di un giocatore
    public String getName() {
        return name;
    }

    // Ottiene il totale della mano di un giocatore
    public int getTotal() {
        return hand.calculateTotal();
    }


    // Ottiene la scommessa di un giocatore
    public int getBet(){
        return this.bet;
    }


    // Aggiunge una carta alla mano di un giocatore
    public void addCard(Card card) {
        hand.addCard(card);

    }


    // Ottiene le carte del giocatore da stampare come una stringa
    public String getHandString() {
        String str = "carte :" + hand.toString();

        return str;
    }


    // Cancella la mano di un giocatore
    public void clearHand() {
        hand.clearHand();
    }

}