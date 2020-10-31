/*
 * A Hand of cards in black jack
 * Subclass of deck
 *
 * @author Zhuyun Chen
 * @date 10/12/2020
 * @note Part of Assignment 2
 */

public class Hand extends Deck{
    
    private int bet; // Bet place on hand
    private boolean playable; // If the hand can still be played (not stand or burst)
    
    private int numAce; // Record number of ace with value 11
    private int total; // Total value of the Hand
    
    /**
     * Default consturctor
     * If didn't declare bet, bet is 0
     */
    public Hand(){
        this(0);
    }
    
    /**
     * Constructor
     * @param bet, Amount of bet placed for the hand
     */
    public Hand(int bet){
        super();
        this.bet = bet;
        this.playable = true;
        this.numAce = 0;
        this.total = 0;
    }
    
    /**
     * Get bet for the hand
     * @return bet
     */
    public int getBet(){
        return this.bet;
    }
    
    /**
     * Set bet for the hand
     * @param bet, bet to place on hand
     */
    public void setBet(int bet){
        this.bet = bet;
    }
    
    
    /**
     * Get total value of the hand
     * @return int total
     */
    public int getTotal(){
        return this.total;
    }
    
    /**
     * Check if the hand can still be played
     * @return true if not stand or burst, false otherwise
     */
    public boolean playable(){
        return this.playable;
    }
    
    /** Display hand on screen */
    public void display(){
        for (Card card:this.getCards()){
            if (card.isVisible() == true){
                System.out.print(" || ");
                card.display();
                System.out.print(" || ");
            }else{
                System.out.print("Hidden ");
            }
        }
        System.out.print("\n");
    }
    
    /**
     * Draw 1 card to hand from deck
     * @param deck, the deck to draw card
     * @return true if burst after hit, false if not burst
     */
    public boolean hit(Deck deck){
        Card newCard = deck.draw();
        System.out.println();
        System.out.print("Hit with card ");
        newCard.display();
        System.out.println(" add to hand.");
        this.add(newCard);
        System.out.print("Current hand is:");
        this.display();
        if (this.isBurst()){
            this.playable = false;
            return true;
        }
        return false;
    }
    
    /** Ends and maintains the value */
    public void stand(){
        this.playable = false;
        System.out.print("Stand, current hand is:");
        this.display();
    }
    
    /**
     * Double bets for the hand
     * Takes a single hit and immediately stands
     * @param deck, deck to draw card from
     */
    public void doubleUp(Deck deck){
        this.bet = this.bet*2;
        this.hit(deck);
        this.stand();
    }
    
    /**
     * Add card to hand
     * Pre-cond: Should be called only when dealing or by Hit
     * If add an Ace, first check whether should count as 0 or 1
     */
    public void add(Card card){
        String rank = card.getRank();
        if (rank == "A"){
            if (this.total < 10){
                this.total += 11;
                this.numAce += 1;
            }else{
                this.total += 1;
            }
        }else if (rank == "J" || rank == "Q" || rank == "K"){
            this.total += 10;
        }else{
            this.total += Integer.parseInt(rank);
        }
        this.addCard(card);
    }
    
    /**
     * Remove card from hand by index
     * Pre-cond: Should only be called when split
     * @param index, index to remove card
     * @return Card, card removed
     */
    public Card remove(int index){
        String rank = this.getCard(index).getRank();
        if (rank == "A"){
            this.total -= 11;
            this.numAce -= 1;
        }else if (rank == "J" || rank == "Q" || rank == "K"){
            this.total -= 10;
        }else{
            this.total -= Integer.parseInt(rank);
        }
        return this.removeCard(index);
    }
    
    /**
     * Pre-cond: should only be called at end of round
     * Check if a hand beat another hand
     * @return int, 0 for win, 1 for tie, 2 for lose
     */
    public int win(Hand dHand){
        System.out.println();
        int handVal = this.getTotal();
        int dVal = dHand.getTotal();
        System.out.println("Your total is: "+handVal);
        System.out.println("Dealer's total is: "+dVal);
        // If both burst, tie
        if (handVal > 21 && dVal > 21){
            return 1;
        }
        // if burst, lose
        if (handVal > 21){
            return 2;
        }
        // If the other hand burst, win
        if (dVal > 21){
            return 0;
        }
        // If larger than the other hand, win
        if (handVal > dVal){
            return 0;
        }
        // If smaller than the other hand, lose
        if (handVal < dVal){
            return 2;
        }
        // Condition when the value of hand is same
        if (this.isBlackJack() == dHand.isBlackJack()){
            return 1;
        }else if (this.isBlackJack() == true){
            return 0;
        }else{
            return 2;
        }
    }
    
    /**
     * Private method, Check if hand is burst, should only be called by hit()
     * @return true if burst, false if burst
     */
    private boolean isBurst(){
        // If total value less equal to 21, not burst
        if (this.total <= 21){
            return false;
        }
        // When total larger than 21
        while(this.total > 21){
            // If no changable ace, the hand burst, else, change ace value to 1
            if (this.numAce < 1){
                return true;
            }else{
                this.total -= 10;
                this.numAce -= 1;
            }
        }
        return false;
    }
    
    /**
     * Private method, should only be called by win() method
     * Check if the hand is a black jack ( contains 2 cards, 1 Ace and 1 face card)
     * @return true if black jack, false otherwise
     */
    private boolean isBlackJack(){
        // Black jack takes only 2 cards
        if (this.numCards() == 2){
            String c1 = this.getCard(0).getRank();
            String c2 = this.getCard(1).getRank();
            if ( c1.equals("A")){
                if (c2.equals("J") || c2.equals("Q") || c2.equals("K")){
                    return true;
                }
            }else if (c2.equals("A")){
                if (c1.equals("J")|| c1.equals("Q")|| c1.equals("K")){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Reset Hand to make it empty and with same bet
     */
    public void init(){
        this.playable = true;
        this.numAce = 0;
        this.total = 0;
    }
    
    /**
     * Draw last card add to hand
     * @return card, card drawed from hand.
     */
    public Card draw(){
        return removeCard(this.numCards()-1);
    }
}
