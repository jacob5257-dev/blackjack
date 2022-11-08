import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Blackjack {
    public static String getRandomCard() {
        String[] cards = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        int randomIndex = (int) (Math.random() * cards.length);
        return cards[randomIndex];
    }

    public static boolean isOver(int a, int b) {
        return a + b > 21;
    }

    @Contract(pure = true)
    public static int evaluateSum(@NotNull List<Integer> cards) {
        int sum = 0;
        for (Integer card : cards) {
            sum += card;
        }
        return sum;
    }

    public static List<String> dealCards() {
        List<String> cards = new ArrayList<>();
        cards.add(getRandomCard());
        cards.add(getRandomCard());
        return cards;
    }

    public static List<Integer> parseCardValues(List<String> cards) {
        List<Integer> cardValues = new ArrayList<>();
        int currentSum = 0;
        for (String card : cards) {
            if (Objects.equals(card, "J") || Objects.equals(card, "Q") || Objects.equals(card, "K")) {
                currentSum += 10;
                cardValues.add(10);
            } else if (!Objects.equals(card, "A")) {
                currentSum += Integer.parseInt(card);
                cardValues.add(Integer.parseInt(card));
            } else {
                //card is ace, need to determine if sum is over 21
                //if it is, then ace is 1, otherwise ace is 11
                if (isOver(currentSum, 11)) {
                    currentSum += 1;
                    cardValues.add(1);
                } else {
                    currentSum += 11;
                    cardValues.add(11);
                }
            }
        }
        if (currentSum >= 21 && cardValues.contains(11)) {
            //if sum is over 21 and there is an ace, then change ace to 1
            cardValues.set(cardValues.indexOf(11), 1);
        }
        return cardValues;
    }

    public static String status(int sum) {
        if (sum == 21) {
            return "blackjack";
        } else if (sum > 21) {
            return "bust";
        } else {
            return "alive";
        }
    }
}
