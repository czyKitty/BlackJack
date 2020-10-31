/*
 * Deck specified for black jack game
 * Subclass of Deck
 *
 * @author Zhuyun Chen
 * @date 10/12/2020
 * @note Part of Assignment 2
 */
public class BJDeck extends Deck {

    private String[] suitList = {"Spade","Heart","Clubs","Diamond"};
    private String[] rankList = {"A","K","Q","J","10","9","8","7","6","5","4","3","2"};
    
    /** Default Constructor */
    public BJDeck(){
        super();
    }
    
    /**
     * Initialize the deck with 52 poker cards, without jokers
     * Randomly shuffle the deck after add all cards
     */
    public void init(){
        for (String suit: this.suitList){
            for (String rank: this.rankList){
                this.addCard(new Card(suit,rank));
            }
        }
        this.shuffle();
    }
    
    /**
     * Reset the deck by empty it and initialize the deck
     */
    public void reset(){
        this.clear();
        this.init();
    }
    
    /**
     * Draw a card from the deck
     * If deck is empty, add a new set of card
     * @return Card, card draw from deck
     */
    public Card draw(){
        if (isEmpty() == true){
            System.out.println("Note: New card set added to deck.\n");
            this.init();
            this.shuffle();
        }
        return this.removeCard(0);
    }
        
}
