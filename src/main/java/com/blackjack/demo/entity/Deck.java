package com.blackjack.demo.entity;
import java.util.ArrayList;
import java.util.Collections;
 
public class Deck {
    private ArrayList<Card> allCards;

    public Deck() {
        this.allCards = new ArrayList<>();
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                this.allCards.add(new Card(s, r));
            }

        }
    }
        public void shuffle(){
            Collections.shuffle(this.allCards);
        }

    public Card draw() {
        return this.allCards.remove(0);
    }

    @Override
    public String toString() {
        return this.allCards.toString();
    }
}

