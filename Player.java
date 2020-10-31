/*
 * The player involved in game
 * Super class for Gambler and Dealer
 *
 * @author Zhuyun Chen
 * @date 10/12/2020
 * @note Part of Assignment 2
 */
import java.util.*;

public abstract class Player {

    protected String name;
    protected ArrayList<Hand> hands;

    /** Default Constructor */
    public Player(){
        this.name = "";
        this.hands = new ArrayList<Hand>();
    }
    
    /** Constructor with self-defined name*/
    public Player(String name){
        this.name = name;
        this.hands = new ArrayList<Hand>();
    }
    
    /**
     * Get name of the player
     * @return name of the player
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Player get a new hand
     * @param hand, new hand to player
     */
    public void addHand(Hand hand){
        this.hands.add(hand);
    }
    
    /**
     * Return the set of hands a player has
     * @return List of hands
     */
    public ArrayList<Hand> getHands(){
        return this.hands;
    }
    
    /** Show the player's hands, could be extend to different type of player */
    public abstract void showHands();
    
    /**
     * Player play the game, could be extend to different type of player
     * @param Deck, deck to pull cards while playing
     */
    public abstract void play(Deck deck);
    
    /**
     * Reset while play new round, could be extend to different type of player
     */
    public abstract void reset();
        
}
