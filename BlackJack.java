import java.util.*;

/**
 * main class for play the Black Jack game
 *
 * @author Zhuyun Chen
 * @date 10/12/2020
 * @note Part of Assignment 2
 */

public class BlackJack {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BJGame game = new BJGame();
        
        System.out.println();
        System.out.println("Welcome to Black Jack Game.");
        System.out.println("You goal is to have a hand value as close as to 21 without burst (get above 21).");
        System.out.println("Have fun!");
        // Initialize the game settings
        System.out.println();
        game.init();
        // Play the game
        game.play();
        System.out.println();
        System.out.println("Thank you for playing!");
    }
}
