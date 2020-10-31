/*
 * Black Jack Game
 *
 * @author Zhuyun Chen
 * @date 10/12/2020
 * @note Part of Assignment 2
 */
import java.util.*;

public class BJGame extends Game {
    
    /**
     * Default Constructor
     */
    public BJGame(){
        super();
        this.deck = new BJDeck();
    }
    
    /**
     * Initialize the Game settings
     * Add the 1 gambler and 1 dealer to table
     * Initialize the deck on table
     */
    public void init(){
        Scanner in = new Scanner(System.in);
        // Ask player to select their name and total chips
        System.out.print("Gambler, please enter your name:");
        String gName = in.nextLine();
        System.out.print("Gambler, please enter your total amount of chips:");
        String chips;
        boolean enterChip;
        do{
            enterChip = false;
            chips = in.nextLine();
            // Check if chip is an int
            if (chips.matches("\\d+") == true){
                this.gamblerList.add(new Gambler(gName,Integer.parseInt(chips)));
            }else {
                System.out.print("Amount of chips must be a positive integer, please enter again:");
                enterChip = true;
            }
        }while (enterChip == true);
        
        // Ask player to choose game mode
        String gameMode;
        System.out.print("Please select a game mode (1.AI 2.Human dealer):");
        boolean selectMode;
        do{
            selectMode = false;
            gameMode = in.nextLine();
            if (gameMode.equals("1")){
                this.dealer = new Dealer();
            }else if (gameMode.equals("2")){
                System.out.println("Dealer, please enter your name:");
                Scanner dName = new Scanner(System.in);
                this.dealer = new HumanDealer(in.nextLine());
            }else{
                System.out.print("Invalid option, please select a game mode (1.AI 2.Human dealer):");
                selectMode = true;
            }
        }while(selectMode == true);
        this.deck.init();
    }
    
    
    /**
     * Play the Black Jack Game
     * Dealer deal cards
     */
    public void play(){
        // Continue the game while all players still have chips
        boolean gamePlay;
        do{
            gamePlay = true;
            // Dealer deals the card
            System.out.println();
            System.out.println("Dealing cards...");
            System.out.println();
            this.dealer.dealCard(this.gamblerList,this.deck);
            Hand dHand = this.dealer.getHands().get(0);
            // Ask all player to place a bet on hand
            Scanner in = new Scanner(System.in);
            for (Gambler g: this.gamblerList){
                System.out.print("Gambler "+g.getName()+", your hand is:");
                g.getHands().get(0).display();
                System.out.print("Place your bet on original hand:");
                String gBet;
                boolean enterBet;
                do{
                    enterBet = false;
                    gBet = in.nextLine();
                    if (gBet.matches("\\d+") == false){
                        System.out.print("Please enter an int for your bet:");
                        enterBet = true;
                    }else if(Integer.parseInt(gBet) > g.getChips()){
                        System.out.print("You can only bet less or equal to your chip:");
                        enterBet = true;
                    }
                }while(enterBet);
                int numBet = Integer.parseInt(gBet);
                g.getHands().get(0).setBet(numBet);
                g.useChip(numBet);
            }
            // When there is still valid hand on table, game continue
            while (this.validHand()){
                // Loop through each gambler to take actions for each valid hand they have
                for (Gambler gambler: this.gamblerList){
                    for (Hand hand: gambler.getHands()){
                        if (hand.playable()){
                            System.out.println();
                            System.out.print("Gambler "+gambler.getName()+"'s turn with hand:");
                            hand.display();
                            System.out.print("Dealer's card': ");
                            dHand.display();
                            System.out.println("Sum: "+hand.getTotal());
                            System.out.println("Bet: "+hand.getBet());
                            gambler.play(this.deck);
                        }
                    }
                }
            }
            System.out.println();
            System.out.println("All players end turn, wait for dealer...");
            // When all players done, dealer flip the face-down card and take actions
            this.dealer.play(this.deck);
            dHand = this.dealer.getHands().get(0);
            // Calculate chips at end of round
            for (Gambler g:this.gamblerList){
                g.calcChip(dHand);
                System.out.println();
                System.out.println("Player "+g.getName()+", you have "+g.getChips()+" chips remaining.");
                if (g.getChips() <= 0){
                    System.out.println("You lose.");
                    gamePlay = false;
                }
                g.reset();
            }
            this.dealer.reset();
            // Allow player to quit the game after each round
            if (gamePlay){
                System.out.print("Type q to quit or anything else to continue play:");
                in = new Scanner(System.in);
                if (in.nextLine().equals("q")){
                    gamePlay = false;
                }
            }
        }while(gamePlay);
    }
    
    /**
     * Check if there's any player with a playble hand
     * Can only be called by play()
     * @return boolean, true if there is a playble hand, false otherwise
     */
    private boolean validHand(){
        for (Gambler g: this.gamblerList){
            for (Hand h:g.getHands()){
                if (h.playable() == true){
                    return true;
                }
            }
        }
        return false;
    }
}
