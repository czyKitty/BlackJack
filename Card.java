/*
 * Card object
 *
 * @author Zhuyun Chen
 * @date 10/12/2020
 * @note Part of Assignment 2
 */
public class Card {

    private String suit;
    private String rank;
    private boolean visible;

    /**
     * Default Constructor
     * A black card with no suit and rank
     */
    public Card(){
        this.suit = "Blank";
        this.rank = "None";
        this.visible = true;
    }
    
    /**
     * Constructor
     * @param suit, suit of the card
     * @param rank, rank of the card
     */
    public Card(String suit, String rank){
        this.suit = suit;
        this.rank = rank;
        this.visible = true;
    }
    
    /**
     * Get suit of the card
     * @return suit of the card
     */
    public String getSuit(){
        return this.suit;
    }
    
    /**
     * Get rank of the card
     * @return rank of the card
     */
    public String getRank(){
        return this.rank;
    }
    
    /**
     * Check if the card is visible to other players
     * @return boolean, true is visible, false otherwise
     */
    public boolean isVisible(){
        return this.visible;
    }
    
    /**
     * Flip the card
     * If it's face-up, make it face-down, same for face-down card
     */
    public void flip(){
        this.visible = !this.visible;
    }
    
    
    /**
     * Display the card by Suit Rank (e.g. Heart 1)
     */
    public void display(){
        System.out.print(this.suit+" "+this.rank);
    }
}
