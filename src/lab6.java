import java.util.Scanner;

public class lab6 {

    public static LinkedList initialize_deck() {

        LinkedList deck = new LinkedList();
        deck.size = 52;

        // populate linked list with a single deck of cards
        for (Card.suites s : Card.suites.values()) {
            for(Card.ranks r : Card.ranks.values()) {
                if (r != Card.ranks.NULL && s != Card.suites.NULL) {
                    Card newCard = new Card(s, r);
                    //newCard.print_card();
                    deck.add_at_tail(newCard);
                }
            }
        }

        return deck;
    }

    private static void play_blind_mans_bluff(LinkedList player1, LinkedList computer, LinkedList deck) {
        System.out.println("\nStarting Blind mans Bluff \n");

        Scanner scnr = new Scanner(System.in);

        // tracker variables
        int rounds = 1;
        int wins = 0;
        int losses = 0;
        int lossesInARow = 0;

        // one game of Blind Man's Buff is 5 rounds
        // for loop iterates 5 times
        for (int i = 0; i < 5; i++) {
            // if player has lost 3 in a row, rage_quit activates
            if(lossesInARow == 3) {
                rage_quit(player1, computer, deck);
                System.out.println("Rage quit activated, restarting game");
                // resets all tracker variables for a fresh set of 5 rounds
                i = 0;
                rounds = 1;
                wins = 0;
                losses = 0;
                lossesInARow = 0;
            }
            // print statements
            System.out.println("Round " + rounds);
            System.out.println();
            System.out.print("Opponent's card: ");
            // removes card from computer's stack of cards and assigns to computer's current card
            Card computerCard = computer.remove_from_head();
            computerCard.print_card();
            System.out.println();
            // removes card from player's stack of cards and assigns to player's current card
            Card playerCard = player1.remove_from_head();
            // player's guess
            System.out.println("Is your card higher or lower? ");
            String playerGuess = scnr.nextLine();
            // assigns correct answer based on result of comparing the two cards
            String correctAnswer ="";
            if(computerCard.compareTo(playerCard) == -1)
                correctAnswer = "higher";
            if(computerCard.compareTo(playerCard) == 1)
                correctAnswer = "lower";
            // if player guesses correctly
            if(playerGuess.equals(correctAnswer)) {
                wins++;
                lossesInARow = 0;
                System.out.print("Round won! Your card was: ");
                playerCard.print_card();
                System.out.println();
            }
            // if player guesses incorrectly
            else {
                losses++;
                lossesInARow++;
                System.out.print("Round lost. Your card was: ");
                playerCard.print_card();
                System.out.println();
            }
            rounds++;
        }
        // prints out game statistics
        System.out.println("Game statistics");
        System.out.println("Games played: " + (rounds - 1));
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
    }

    public static void rage_quit(LinkedList playerCards, LinkedList computerCards, LinkedList deck) {
        deck = initialize_deck(); // reset deck
        deck.shuffle(512); // reshuffle deck
        // re-deals five cards
        int num_cards_dealt = 5;
        for (int i = 0; i < num_cards_dealt; i++) {
            // player removes a card from the deck and adds to their hand
            playerCards.add_at_tail(deck.remove_from_head());
            computerCards.add_at_tail(deck.remove_from_head());
        }

    }

    public static void main(String[] args) {

        // create a deck (in order)
        LinkedList deck = initialize_deck();
        deck.print();
        deck.sanity_check(); // because we can all use one

        // shuffle the deck (random order)
        deck.shuffle(512);
        deck.print();
        deck.sanity_check(); // because we can all use one

        // cards for player 1 (hand)
        LinkedList player1 = new LinkedList();
        // cards for player 2 (hand)
        LinkedList computer = new LinkedList();

        int num_cards_dealt = 5;
        for (int i = 0; i < num_cards_dealt; i++) {
            // player removes a card from the deck and adds to their hand
            player1.add_at_tail(deck.remove_from_head());
            computer.add_at_tail(deck.remove_from_head());
        }

        // let the games begin!
         play_blind_mans_bluff(player1, computer, deck);
    }
}
