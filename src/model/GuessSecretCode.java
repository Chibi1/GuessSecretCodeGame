package model;

import java.util.Scanner;

public class GuessSecretCode {

    private int numberOfGuessesRemaining;

    private int secretCode;
    private String secretCodeString;
    private int lastGuess;
    private String lastGuessString;
    private boolean isSecretCodeFound;
    private int numberOfCorrectDigits;
    private int[] correctDigits = new int[5];
    private int sumOfCorrectDigits;

    private Scanner scanner = new Scanner(System.in);

    public GuessSecretCode() {
        numberOfGuessesRemaining = 5;
        generateCode();
        this.isSecretCodeFound = false;
    }

    public void playGame() {
        System.out.printf("%s %d %s\n%s\n", "You have", numberOfGuessesRemaining,
                "chances to guess the magic 5 digit number",
                "For example: 45873");
        do {
            promptUserForGuess();
            analyseGuess();
            printResults();
        } while (numberOfGuessesRemaining > 0 && !isSecretCodeFound);

    }

    private void generateCode() {
        secretCode = (int)(Math.random() * 100000);
        secretCodeString = Integer.toString(secretCode);
    }

    private void promptUserForGuess() {
        System.out.print("What is your guess? ");
        lastGuess = scanner.nextInt();
        lastGuessString = Integer.toString(lastGuess);
        numberOfGuessesRemaining--;
    }

    private void analyseGuess() {
        if (lastGuess == secretCode) {
            isSecretCodeFound = true;
        } else {
            calculateHowManyCorrectNumbers();
            calculateValueOfCorrectDigits();
        }
    }

    private void calculateHowManyCorrectNumbers() {
        numberOfCorrectDigits = 0;
        for (int i = 0; i < secretCodeString.length(); i++) {
            if (secretCodeString.contains(lastGuessString.subSequence(i, i+1))) {
                numberOfCorrectDigits++;
                storeMatchingDigits(i);
            }
        }
    }

    private void storeMatchingDigits(int index) {
        correctDigits[index] = Character.getNumericValue(lastGuessString.charAt(index));
    }

    private void calculateValueOfCorrectDigits() {
        sumOfCorrectDigits = 0;
        for (int i = 0; i < correctDigits.length; i++) {
            sumOfCorrectDigits += correctDigits[i];
        }

    }

    private void printResults() {
        if (isSecretCodeFound) {
            System.out.println("Congratulations! You found the secret code!");
        } else {
            switch (numberOfCorrectDigits) {
                case 0:
                    System.out.print("None ");
                    break;
                case 1:
                    System.out.print("One ");
                    break;
                case 2:
                    System.out.print("Two ");
                    break;
                case 3:
                    System.out.print("Three ");
                    break;
                case 4:
                    System.out.print("Four ");
                    break;
            }
            System.out.println("of your numbers matched a number in the secret number");
            System.out.printf("%s %d\n", "The total of the values of the matching numbers is", sumOfCorrectDigits);
            System.out.printf("%s %d %s\n", "In that guess, you had", numberOfCorrectDigits, "matching numbers");
            System.out.printf("%s %d\n\n", "Guesses remaining =", numberOfGuessesRemaining);
        }

    }

}
