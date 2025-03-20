// Standard French-style cards
public class Card {

    // Suites
    public enum suites {
        NULL, SPADES, CLUBS, DIAMONDS, HEARTS
    }

    // Ranks
    public enum ranks {
        NULL, two, three, four, five, six, seven, eight, nine, ten, jack, queen, king, ace
    }

    private suites suit;
    private ranks rank;

    Card(){
        suit = suites.NULL;
        rank = ranks.NULL;
    }

    Card(suites s, ranks r){
        suit = s;
        rank = r;
    }

    public void print_card(){
        System.out.print(suit + ": " + rank);
    }

    // compares cards
    public int compareTo(Card card) {
        // ordinal() returns the integer position of an enum constant
        // comparing the positions of the constants is the same as comparing the numeric value of the cards

        if (this.rank.ordinal() > card.rank.ordinal()) {
            return 1;
        }
        else if (this.rank.ordinal() < card.rank.ordinal()) {
            return -1;
        }
        // if numeric values of cards are equal, compare the ordinal value of their suits
        else {
            return Integer.compare(this.suit.ordinal(), card.suit.ordinal());
        }
    }

}
