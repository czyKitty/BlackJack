/*
 * Player controled dealer in blackjack game
 * Subclass of Dealer
 *
 * @author Zhuyun Chen
 * @date 10/12/2020
 * @note Part of Assignment 2
 */
import java.util.*;

public class HumanDealer extends Dealer{
    
    /**
     * Constructor
     * @param name, name of the player dealer
     */
    public HumanDealer(String name){
        super(name);
    }
    
    /**
     * Deal cards to all players (with dealer decide the sequence to give card)
     * Pre-cond: should only called at beginning of each round
     * @param pList, list of gamblers deals card to
     * @param deck, the deck to draw card
     */
    public void dealCard(ArrayList<Gambler> pList, Deck deck){
        Hand newHand;
        int numPlayer = pList.size();
        System.out.println("Dealer "+this.getName()+", there are "+numPlayer+" on the table.");
        // deal 1st card to players
        System.out.print("Which order you want deal card (1. Left to Right. 2.Right to Left):");
        Scanner in = new Scanner(System.in);
            boolean endInput;
            do{
                endInput = true;
                String order = in.nextLine();
                if (order.equals("1")){
                    for (int i=0; i<pList.size(); i++){
                        newHand = new Hand();
                        newHand.add(deck.draw());
                        pList.get(i).addHand(newHand);
                    }
                    for (int i=pList.size()-1; i>=0; i--){
                        pList.get(i).getHands().get(0).add(deck.draw());
                    }
                }else if (order.equals("2")){
                    for (int i=pList.size()-1; i>=0; i--){
                        newHand = new Hand();
                        newHand.add(deck.draw());
                        pList.get(i).addHand(newHand);
                    }
                    for (int i=0; i<pList.size(); i++){
                        pList.get(i).getHands().get(0).add(deck.draw());
                    }
                }else{
                    System.out.println("Invalid input, please select order (1. Left to Right. 2.Right to Left):");
                    endInput = false;
                }
            }while(endInput = false);
        
        
        // Draw 1 card as hand to dealer
        System.out.print("Deal card to dealer"+this.getName());
        newHand = new Hand();
        Card dCard = deck.draw();
        newHand.add(dCard);
        this.addHand(newHand);
        System.out.print(", card is ");
        dCard.display();
        System.out.println();
        System.out.print("Deal hidden card to dealer"+this.getName());
        this.faceDown = deck.draw();
        System.out.print(", card is ");
        faceDown.display();
        System.out.println("\n");
        this.faceDown.flip();
    }
    
    /**
     * Dealer's turn
     * Should only be called after all gamblers stand
     * Conitune hit until reach 17, then stand
     * @param deck, deck to draw cards
     */
    public void play(Deck deck){
        System.out.println("All players stand, dealer "+this.getName()+"take actions.");
        System.out.print("Flip the hidden card, hidden card is: ");
        this.faceDown.display();
        System.out.print("\n");
        // flip the face-down card
        this.showHide();
        Hand dHand = this.hands.get(0);
        // Hit untile reach 17
        while (dHand.getTotal() < 17){
            System.out.println("Current total is "+dHand.getTotal()+", continue hit.");
            dHand.hit(deck);
        }
        System.out.println("Total value above 17, stand.");
        dHand.stand();
    }
    

}
