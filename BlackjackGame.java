package com.company;

import java.util.Scanner;


public class BlackjackGame {

    private Scanner ki = new Scanner(System.in);
    private int users;
    private Player[] players;
    private Deck deck;
    private Dealer dealer = new Dealer();

    // Starts game and displays the rules
    public void initializeGame(){
        String names;


        // Gets the amount of players and creates them
        do {
            System.out.print("In quante persone vuoi giocare (1-6)? ");
            users = ki.nextInt();


        } while (users > 6 || users < 0);

        players = new Player[users];
        deck = new Deck();

        // Asks for player names and assigns them
        for (int i = 0; i < users; i++) {
            System.out.print("Quale è il nome del Giocatore " + (i + 1) + "? ");
            names = ki.next();
            players[i] = new Player();
            players[i].setName(names);
        }
    }

    // Shuffles the deck
    public void shuffle() throws InvalidDeckPositionException, InvalidCardSuitException, InvalidCardValueException {
        deck.shuffle();

    }

    // Gets the bets from the players
    public void getBets(){
        int betValue;

        for (int i =0; i < users; i++) {
            if (players[i].getBank() > 0) {
                do {
                    System.out.print("Quanto vuoi giacare " + players[i].getName()  + (" (1-" + players[i].getBank()) + ")? " );
                    betValue = ki.nextInt();
                    players[i].setBet(betValue);
                } while (!( betValue > 0 && betValue <= players[i].getBank()));
                System.out.println("");
            }

        }

    }

    // Deals the cards to the players and dealer
    public void dealCards(){
        for (int j = 0; j < 2; j++) {
            for (int i =0; i < users; i++) {
                if(players[i].getBank() > 0)
                {
                    players[i].addCard(deck.nextCard());
                }
            }

            dealer.addCard(deck.nextCard());
        }
    }

    // Initial check for dealer or player Blackjack
    public void checkBlackjack(){
        //System.out.println();

        if (dealer.isBlackjack() ) {
            System.out.println("Il Banco ha il BlackJack!");
            for (int i =0; i < users; i++) {
                if (players[i].getTotal() == 21 ) {
                    System.out.println(players[i].getName() + " vinto");
                    players[i].push();
                } else {
                    System.out.println(players[i].getName() + " perso");
                    players[i].bust();
                }
            }
        } else {
            if (dealer.peek() ) {
                System.out.println("Il Banco gioca e non ha il BlackJack");
            }

            for (int i =0; i < users; i++) {
                if (players[i].getTotal() == 21 ) {
                    System.out.println(players[i].getName() + " ha il blackjack!");
                    players[i].blackjack();
                }
            }
        }
    }
    // Codice per stabilre se Chiedere Carta (C) o Stare (T)
    public void hitOrStand() {
        String command;
        char c;
        for (int i = 0; i < users; i++) {
            if ( players[i].getBet() > 0 ) {
                System.out.println();
                System.out.println(players[i].getName() + " ha " + players[i].getHandString());

                do {
                    do {
                        System.out.print(players[i].getName() + " (C)arta o (T)ermina? ");
                        command = ki.next();
                        c = command.toUpperCase().charAt(0);
                    } while ( ! ( c == 'C' || c == 'T' ) );
                    if ( c == 'C' ) {
                        players[i].addCard(deck.nextCard());
                        System.out.println(players[i].getName() + " ha " + players[i].getHandString());
                    }
                } while (c != 'T' && players[i].getTotal() <= 21 );
            }
        }
    }

    // Code for the dealer to play
    public void dealerPlays() {
        boolean isSomePlayerStillInTheGame = false;
        for (int i =0; i < users && isSomePlayerStillInTheGame == false; i++){
            if (players[i].getBet() > 0 && players[i].getTotal() <= 21 ) {
                isSomePlayerStillInTheGame = true;
            }
        }
        if (isSomePlayerStillInTheGame) {
            dealer.dealerPlay(deck);
        }
    }

    // This code calculates all possible outcomes and adds or removes the player bets
    public void settleBets() {
        System.out.println();

        for (int i = 0; i < users; i++) {
            if (players[i].getBet() > 0 ) {
                if( players[i].getTotal() > 21 ) {
                    System.out.println(players[i].getName() + " ha sballato");
                    players[i].bust();
                } else if ( players[i].getTotal() == dealer.calculateTotal() ) {
                    System.out.println(players[i].getName() + " ha pareggiato");
                    players[i].push();
                } else if ( players[i].getTotal() < dealer.calculateTotal() && dealer.calculateTotal() <= 21 ) {
                    System.out.println(players[i].getName() + " ha perso");
                    players[i].loss();
                } else if (players[i].getTotal() == 21) {
                    System.out.println(players[i].getName() + " ha vinto col Blackjack!");
                    players[i].blackjack();
                } else {
                    System.out.println(players[i].getName() + " ha vinto");
                    players[i].win();

                }
            }
        }

    }

    // This prints the players hands
    public void printStatus() {
        for (int i = 0; i < users; i++) {
            if(players[i].getBank() > 0)
            {
                System.out.println(players[i].getName() + " ha " + players[i].getHandString());
            }
        }
        System.out.println("Il Banco ha " + dealer.getHandString(true, true));
    }

    // This prints the players banks and tells the player if s/he is out of the game
    public void printMoney() {
        for (int i = 0; i < users; i++) {
            if(players[i].getBank() > 0)
            {
                System.out.println(players[i].getName() + " ha " + players[i].getBank());
            }
            if(players[i].getBank() == 0)
            {
                System.out.println(players[i].getName() + " ha " + players[i].getBank() + " è uscito dal gioco .");
                players[i].removeFromGame();
            }
        }
    }

    // This code resets all hands
    public void clearHands() {
        for (int i = 0; i < users; i++) {
            players[i].clearHand();
        }
        dealer.clearHand();

    }

    // This decides to force the game to end when all players lose or lets players choose to keep playing or not
    public boolean playAgain() {
        String command;
        char c;
        Boolean playState = true;
        if(forceEnd()) {
            playState = false;
        }
        else {
            do {
                System.out.println("");
                System.out.print("vuoi giocare ancora (s)i (n)o ? ");
                command = ki.next();
                c = command.toUpperCase().charAt(0);
            } while ( ! ( c == 's' || c == 'n' ) );
            if(c == 'n')
            {
                playState = false;
            }
        }
        return playState;
    }

    // This says true or false to forcing the game to end
    public boolean forceEnd() {
        boolean end = false;
        int endCount = 0;

        for (int i = 0; i < users; i++) {
            if(players[i].getBank() == -1)
            {
                endCount++;
            }
        }
        if(endCount == users)
        {
            end = true;
        }
        if(end)
        {
            System.out.println("");
            System.out.println("tutti i giocatori hanno smesso di giocare ( fine partita )");
        }

        return end;
    }

    // This is the endgame code for when all players are out of the game or players decide to stop playing
    public void endGame() {
        int endAmount;
        String endState = "nessun cambio ";
        System.out.println("");
        for (int i = 0; i < users; i++) {
            if(players[i].getBank() == -1)
            {
                players[i].resetBank();
            }
            endAmount = players[i].getBank() - 100;
            if(endAmount > 0)
            {
                endState = " guadagno di  ";
            }
            else if(endAmount < 0)
            {
                endState = " perdita di  ";
            }
            System.out.println(players[i].getName() + " ha terminato il gioco con  " + players[i].getBank() + ".");
            if(endState != "nessun cambio")
            {
                System.out.println("A" + endState + Math.abs(endAmount) + ".");
            }
            else
            {
                System.out.println("nessun cambio rispetto ai valori iniziali ");
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("grazie per aver giocato ");
    }


}