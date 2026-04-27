package com.blackjack.demo.entity;

import java.util.ArrayList;

public class Player {
  public ArrayList<Card> hand;
  public int egyenleg = 1000;
  public Player() {
      this.hand = new ArrayList<>();
  }
    public void addCard(Card c) {
        this.hand.add(c);
    }


    public int getPoints(){
      int numberPoints = 0;
      int numberAces = 0;

      for (Card c : this.hand){
          Rank r = c.getRank();

          switch (r) {
              case TWO: numberPoints +=2; break;
              case THREE: numberPoints +=3;break;
              case FOUR: numberPoints +=4;break;
              case FIVE: numberPoints +=5; break;
              case SIX: numberPoints+=6; break;
              case SEVEN:numberPoints+=7; break;
              case EIGHT: numberPoints+=8; break;
              case NINE: numberPoints+=9;break;
              case TEN:
              case JACK:
              case QUEEN:
              case KING: numberPoints+=10; break;
              case ACE:
                  numberPoints+=11;
                  numberAces++;
                  break;
          }
      }
      while(numberPoints>21 && numberAces>0){
          numberPoints-=10;
          numberAces--;
      }
      return numberPoints;
    }


    @Override
    public String toString() {
        return "Játékos lapjai: " + this.hand.toString();
    }
    }

