import java.util.List;

public class Dealer {
    public static Integer getDealerSum() {
        List<String> cards = Blackjack.dealCards();
        System.out.println("The dealer was dealt the cards: " + cards.get(0) + " and " + cards.get(1));
        System.out.println("The sum of the dealer's cards is: " + Blackjack.evaluateSum(Blackjack.parseCardValues(cards)));
        boolean isOver = false;
        int finalSum = 0;
        while (!isOver) {
            if (Blackjack.evaluateSum(Blackjack.parseCardValues(cards)) < 17) {
                cards.add(Blackjack.getRandomCard());
                System.out.println("The dealer was dealt the card: " + cards.get(cards.size() - 1));
                System.out.println("The sum of the dealer's cards is: " + Blackjack.evaluateSum(Blackjack.parseCardValues(cards)));
                String status = Blackjack.status(Blackjack.evaluateSum(Blackjack.parseCardValues(cards)));
                if (status.equals("bust")) {
                    System.out.println("The dealer went over 21! You win!");
                    finalSum = Blackjack.evaluateSum(Blackjack.parseCardValues(cards));
                    isOver = true;
                } else if (status.equals("blackjack")) {
                    System.out.println("The dealer got blackjack! You lose!");
                    finalSum = Blackjack.evaluateSum(Blackjack.parseCardValues(cards));
                    isOver = true;
                }
            } else {
                System.out.println("The dealer chose to stand.");
                finalSum = Blackjack.evaluateSum(Blackjack.parseCardValues(cards));
                isOver = true;
            }
        }
        return finalSum;
    }
}
