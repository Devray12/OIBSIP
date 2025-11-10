import java.util.Scanner;
import java.util.Random;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int totalScore = 0;
        int round = 1;

        System.out.println(" Welcome to the Guess The Number Game!");
        System.out.println("=======================================");

        while (true) {
            System.out.println("\nRound " + round + ":");
            int numberToGuess = random.nextInt(100) + 1;  // generates number between 1 and 100
            int attempts = 0;
            int maxAttempts = 7;
            boolean guessed = false;

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess (1 to 100): ");
                int userGuess = sc.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Correct! You guessed it in " + attempts + " attempts!");
                    int points = (maxAttempts - attempts + 1) * 10;
                    totalScore += points;
                    System.out.println("You earned " + points + " points this round!");
                    guessed = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!guessed) {
                System.out.println("Out of attempts! The number was " + numberToGuess);
            }

            System.out.println("Your total score so far: " + totalScore);
            System.out.print("\nDo you want to play another round? (yes/no): ");
            String choice = sc.next().toLowerCase();

            if (!choice.equals("yes")) {
                System.out.println("\n Game Over!");
                System.out.println("Your final score: " + totalScore);
                System.out.println("Thanks for playing! ");
                break;
            }

            round++;
        }

        sc.close();
    }
}
