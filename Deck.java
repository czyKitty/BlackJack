/*
 * Deck of cards
 * Super class of DeckBJ and Hand
 *
 * @author Zhuyun Chen
 * @date 10/12/2020
 * @note Part of Assignment 2
 */
import java.util.*;

public abstract class Deck {

    private ArrayList<Card> cards;

    /** Default Constructor */
    public Deck(){
        this.cards = new ArrayList<Card>();
    }
    
    /**
     * Initialize the deck
     * Can be extend to different game type (cards used for deck may vary)
     */
    public abstract void init();
    
    /**
     * Draw a card from the deck
     * Can be extend to different game type (rules for draw may vary)
     */
    public abstract Card draw();
    
    /**
     * Get number of cards in deck
     * @return int, number of cards in deck
     */
    public int numCards(){
        return this.cards.size();
    }
    
    /**
     * Get all cards in deck
     * @return cards, list of cards in deck
     */
    public ArrayList<Card> getCards(){
        return this.cards;
    }
    
    /**
     * Get card at specific position
     * @return card, card at given position
     */
    public Card getCard(int index){
        return this.cards.get(index);
    }
    
    /**
     * Add new card to deck
     * @param card, card to be add
     */
    public void addCard(Card card){
        this.cards.add(card);
    }
    
    /**
     * Pre-cond: index must be valid
     * Remove a card from deck by index and return it
     * @param index, index of card to be remove
     * @return Card, card removed
     */
    public Card removeCard(int index){
        return this.cards.remove(index);
    }
    
    /**
     * Randomly shuffle the deck
     */
    public void shuffle(){
        Collections.shuffle(this.cards);
    }
    
    /**
     * Remove all cards from the deck
     */
    public void clear(){
        this.cards.clear();
    }

    /**
     * Check if a Deck is empty
     * @return true if no card in deck, false otherwise
     */
    public boolean isEmpty(){
        if (cards.size() < 1){
            return true;
        }
        return false;
    }

}
