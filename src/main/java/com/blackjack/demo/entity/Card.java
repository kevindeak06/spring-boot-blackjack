package com.blackjack.demo.entity;

public class Card {
    private Suit suit;
    private Rank rank;
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    public Rank getRank(){
        return this.rank;
        }



    @Override
    public String toString() {
        return this.suit + " " + this.rank;
    }

    public String getKepUrl() {
        String alapUrl = "https://raw.githubusercontent.com/hayeah/playing-cards-assets/master/png/";
        String rangKod = "";

        switch (this.rank) {
            case TWO: rangKod = "2"; break;
            case THREE: rangKod = "3"; break;
            case FOUR: rangKod = "4"; break;
            case FIVE: rangKod = "5"; break;
            case SIX: rangKod = "6"; break;
            case SEVEN: rangKod = "7"; break;
            case EIGHT: rangKod = "8"; break;
            case NINE: rangKod = "9"; break;
            case TEN: rangKod = "10"; break;
            case JACK: rangKod = "jack"; break;
            case QUEEN: rangKod = "queen"; break;
            case KING: rangKod = "king"; break;
            case ACE: rangKod = "ace"; break;
            default: rangKod = "2"; break;
        }

        String szinKod = this.suit.toString().toLowerCase();
        return alapUrl + rangKod + "_of_" + szinKod + ".png";
    }


}

