/*
 * General Gambling Game
 *
 * @author Zhuyun Chen
 * @date 10/12/2020
 * @note Part of Assignment 2
 */
import java.util.*;

public abstract class Game {

    protected ArrayList<Gambler> gamblerList;
    protected Dealer dealer;
    protected Deck deck;
    
    /**
     * Default Constructor
     */
    public Game(){
        this.gamblerList = new ArrayList<Gambler>();
    }
    
    
    /** Initialize the Game settings */
    public abstract void init();
    
    
    /** Play the Game */
    public abstract void play();
        
}
