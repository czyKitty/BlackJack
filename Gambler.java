/*
 * Gambler in blackjack game
 * Subtype of player
 *
 * @author Zhuyun Chen
 * @date 10/12/2020
 * @note Part of Assignment 2
 */
import java.util.*;

public class Gambler extends Player{
    
    private int chip;
    private int validHand;

    /** Default Constructor */
    public Gambler(int chip){
        super();
        this.chip = chip;
        this.validHand = 1;
    }
    
    /** Constructor with self-defined name*/
    public Gambler(String name, int chip){
        super(name);
        this.chip = chip;
        this.validHand = 1;
    }
    
    /**
     * Return number of hands that are still valid (not stand or burst)
     * @return Number of valid hand (0 means round end for this player)
     */
    public int validHand(){
        return this.validHand;
    }
    
    /**
     * Return remaining chips
     * @return Number of chips
     */
    public int getChips(){
        return this.chip;
    }
    
    /**
     * Subtract used chips from chip
     * @parma Number of chips used
     */
    public void useChip(int used){
        this.chip -= used;
    }

    /** Display all hands on screen */
    public void showHands(){
        for (Hand hand:this.getHands()){
            hand.display();
        }
    }
    
    /**
     * Play black jack game
     * @param Deck, deck to pull cards while playing
     */
    public void play(Deck deck){
        if (this.validHand > 0){
            // If has multiple hands, treate each playable hand seperatly
            for (Hand hand:this.getHands()){
                if (hand.playable()){
                    this.playHand(hand, deck);
                }
            }
        }
    }
    
    /**
     * Gambler can take 1 of actions (hit/stand/split/doubleUp) for each hand
     * Private method, should only be called by play()
     * @param hand, the hand to be played
     * @param deck, deck to draw cards
     */
    private void playHand(Hand hand, Deck deck){
        String actionOpt = "1.Hit 2.Stand ";
        int numOpt = 2;
        // Player can only split or double up when have enough chip
        if (hand.getBet() <= this.chip){
            // if meet split condition, add split option
            if (hand.numCards() == 2 && hand.getCard(0) == hand.getCard(1)){
                actionOpt += "3.Split ";
                numOpt += 1;
            }
            // Add double up option
            if (numOpt == 3){
                actionOpt += "4.Double Up ";
            }else{
                actionOpt += "3.Double Up ";
            }
            numOpt += 1;
        }
        
        Scanner in = new Scanner(System.in);
        String action;
        System.out.println("Gambler "+this.getName()+", you have action:"+actionOpt);
        System.out.print("please select an action:");
        boolean actionLoop;
        do{
            actionLoop = false;
            action = in.nextLine();
            if (action.equals("1")){
                // If a hand burst after hit, it becomes invalid
                if (hand.hit(deck)){
                    System.out.println("Total is "+hand.getTotal()+", burst!");
                    this.validHand -= 1;
                }
            }else if (action.equals("2")){
                // If a hand stand, it becomes invalid
                hand.stand();
                this.validHand -= 1;
            }else if (numOpt == 3 && action.equals("3")){
                // If double up, hand is stand at end and is invalid
                hand.doubleUp(deck);
                this.validHand -= 1;
            }else if (numOpt == 4 && action.equals("3")){
                this.split(hand, deck);
                this.validHand += 1;
            }else if (numOpt == 4 && action.equals("4")){
                hand.doubleUp(deck);
                this.validHand -= 1;
            }else{
                System.out.println("Invalid action, you only have option:"+actionOpt);
                System.out.print("please select an action:");
                actionLoop = true;
            }
        }while(actionLoop);
    }
    
    /**
     * Split a hand when that hand contains 1 paired card (of same type)
     * New hand contains 1 of the paired card, and bet equal to bet for original hand
     * @param hand, the original hand need to be split
     * @param deck, deck to draw cards
     */
    public void split(Hand hand, Deck deck){
        int newBet = hand.getBet();
        Hand newHand = new Hand(newBet);
        newHand.add(hand.remove(0));
        // Subtract bet from chips
        this.chip -= newBet;
        // Gives a single new cards to each hand
        hand.add(deck.draw());
        newHand.add(deck.draw());
        // Add new hand to hand list
        this.addHand(newHand);
    }
    /**
     * Pre-cond: should only be called at end of round
     * Calculate the chips at end of each game (when all players burst or stand)
     * Possible conditions:
     * 0 win:gain double the bet; 1 tie:return the bet; 2 lose:lose the bet
     * @param dHand, dealer's hand
     */
    public void calcChip(Hand dHand){
        for (Hand hand:this.getHands()){
            int winCond = hand.win(dHand);
            if (winCond == 0){
                System.out.println("You gain "+hand.getBet()+" chips!");
                this.chip += 2*hand.getBet();
            }else if(winCond == 1){
                this.chip += hand.getBet();
            }else{
                System.out.println("You lose "+hand.getBet()+" chips!");
            }
        }
    }
    
    /* reset to beginning */
    public void reset(){
        this.hands.clear();
        this.validHand = 1;
    }

}
