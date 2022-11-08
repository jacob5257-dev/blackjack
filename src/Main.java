import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> cards = Blackjack.dealCards();
        System.out.println("You were dealt the cards: " + cards.get(0) + " and " + cards.get(1));
        System.out.println("The sum of your cards is: " + Blackjack.evaluateSum(Blackjack.parseCardValues(cards)));
        boolean isOver = false;
        int finalSum = 0;
        while (!isOver) {
            System.out.print("Do you want to hit or stand (default = stand)? ");
            String input = new java.util.Scanner(System.in).nextLine();
            if (input.equals("hit")) {
                cards.add(Blackjack.getRandomCard());
                System.out.println("You were dealt the card: " + cards.get(cards.size() - 1));
                System.out.println("The sum of your cards is: " + Blackjack.evaluateSum(Blackjack.parseCardValues(cards)));
                String status = Blackjack.status(Blackjack.evaluateSum(Blackjack.parseCardValues(cards)));
                if (status.equals("bust")) {
                    System.out.println("You went over 21!");
                    finalSum = Blackjack.evaluateSum(Blackjack.parseCardValues(cards));
                    isOver = true;
                } else if (status.equals("blackjack")) {
                    System.out.println("You got blackjack!");
                    finalSum = Blackjack.evaluateSum(Blackjack.parseCardValues(cards));
                    isOver = true;
                }
            } else {
                System.out.println("You chose to stand.");
                finalSum = Blackjack.evaluateSum(Blackjack.parseCardValues(cards));
                isOver = true;
            }
        }
        System.out.println("Your final sum was: " + finalSum);
        if (finalSum > 21) {
            System.out.println("You went over 21! You lose!");
            System.exit(0);
        }
        System.out.println("It's the dealer's turn now.");
        int dealerSum = Dealer.getDealerSum();
        System.out.println("The dealer's final sum was: " + dealerSum);
        if (dealerSum > 21) {
            System.out.println("You win!");
            System.exit(0);
        }
        if (finalSum > dealerSum) System.out.println("You win!");
        else if (finalSum < dealerSum) System.out.println("You lose!");
        else System.out.println("It's a tie!");
    }
}
