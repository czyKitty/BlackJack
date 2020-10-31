/*
 * Dealer in blackjack game
 * Subclass of player
 *
 * @author Zhuyun Chen
 * @date 10/12/2020
 * @note Part of Assignment 2
 */
import java.util.*;

public class Dealer extends Player{
    
    protected Card faceDown; // The card invisible to player
    
    /** Default Constructor */
    public Dealer(){
        super("Dealer");
    }
    
    /** Constructor with self-defined name*/
    public Dealer(String name){
        super(name);
    }
    
    /**
     * Deal cards to all players
     * Pre-cond: should only called at beginning of each round
     * @param pList, list of gamblers deals card to
     * @param deck, the deck to draw card
     */
    public void dealCard(ArrayList<Gambler> pList, Deck deck){
        Hand newHand;
        // Draw 1 cards as hand to each gambler
        for (int i=0; i<pList.size(); i++){
            newHand = new Hand(); 
            newHand.add(deck.draw());
            pList.get(i).addHand(newHand);
        }
        // Draw 1 card as hand to dealer
        newHand = new Hand();
        newHand.add(deck.draw());
        this.addHand(newHand);
        // Draw 1 card facedown
        this.faceDown = deck.draw();
        this.faceDown.flip();
        // Draw another card to gamblers in switched order
        for (int i=pList.size()-1; i>=0; i--){
            pList.get(i).getHands().get(0).add(deck.draw());
        }
    }
    
    /**
     * Show the dealer's hands, display only face-up cards
     */
    public void showHands(){
        this.getHands().get(0).display();
        // If face down card is still hidden, make a note
        if (this.faceDown.isVisible() == false){
            System.out.print("1 Card Hidden");
        }
    }
    
    /**
     * Dealer's turn
     * Should only be called after all gamblers stand
     * Conitune hit until reach 17, then stand
     * @param deck, deck to draw cards
     */
    public void play(Deck deck){
        // flip the face-down card
        this.showHide();
        Hand dHand = this.hands.get(0);
        // Hit until reach 17
        while (dHand.getTotal() < 17){
            boolean isBurst = dHand.hit(deck);
            System.out.println("Dealer hit, total is:"+dHand.getTotal());
            if (isBurst){
                System.out.println("Dealer burst!");
            }
        }
        System.out.println("Dealer stand, totals is:"+dHand.getTotal());
        dHand.stand();
    }
    
    /**
     * Private method to Flip the hidden card
     * should only be called by play() (when dealer take turn)
     */
    protected void showHide(){
        this.faceDown.flip();
        Hand dHand = this.getHands().get(0);
        dHand.add(this.faceDown);
    }
    
    /** reset to beginning */
    public void reset(){
        this.hands.clear();
        this.faceDown = null;
    }
}
