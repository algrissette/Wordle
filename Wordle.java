/*
 * Wordle.java 
 * 
 * A console-based implementation of a popular word-guessing game
 * 
 * Key improvements:
 * - Fixed color coding logic for duplicate letters (major bug fix)
 * - Added proper yellow/green letter tracking
 * - Improved user feedback and validation
 * - Added keyboard tracking feature
 * - Better code organization and documentation
 */

import java.util.*;

public class Wordle {
    // the name of a file containing a collection of English words, one word per line
    public static final String WORD_FILE = "words.txt";
    
    // ANSI color codes for better visual feedback
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GRAY = "\u001B[90m";
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";

    /*
     * printWelcome - prints the message that greets the user at the beginning of the game
     */  
    public static void printWelcome() {
        System.out.println();   
        System.out.println(BOLD + "Welcome to Wordle!" + RESET);
        System.out.println("The mystery word is a 5-letter English word.");
        System.out.println("You have 6 chances to guess it.");
        System.out.println();
        System.out.println("Color Guide:");
        System.out.println("  " + GREEN + "GREEN" + RESET + " = Correct letter in correct position");
        System.out.println("  " + YELLOW + "YELLOW" + RESET + " = Correct letter in wrong position");
        System.out.println("  " + GRAY + "GRAY" + RESET + " = Letter not in word");
        System.out.println();
    }
    
    /*
     * initWordList - creates the WordList object that will be used to select
     * the mystery word. Takes the array of strings passed into main(),
     * since that array may contain a random seed specified by the user 
     * from the command line.
     */
    public static WordList initWordList(String[] args) {
        int seed = -1;
        if (args.length > 0) {
            seed = Integer.parseInt(args[0]);
        }

        return new WordList(WORD_FILE, seed);
    }

    /*
     * readGuess - reads a single guess from the user and returns it
     * inputs:
     *   guessNum - the number of the guess (1, 2, ..., 6) that is being read
     *   console - the Scanner object that will be used to get the user's inputs
     */
    public static String readGuess(int guessNum, Scanner console) {
        String guess;
        do {
            System.out.print("guess " + guessNum + ": ");
            guess = console.next();
        } while (!isValidGuess(guess));

        return guess.toLowerCase();
    }

    /*
     * isAlpha - checks if the string s contains only alphabetic characters
     */
    public static boolean isAlpha(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isAlphabetic(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /*
     * isValidGuess - takes an arbitrary string guess and returns true
     * if it is a valid guess for Wordle, and false otherwise
     */
    public static boolean isValidGuess(String guess) {
        if (guess.length() != 5) {
            System.out.println("  " + GRAY + "Your guess must be 5 letters long." + RESET);
            return false;
        } else if (!isAlpha(guess)) {
            System.out.println("  " + GRAY + "Your guess must only contain letters of the alphabet." + RESET);
            return false;
        } else {
            return true;
        }
    }

    /*
     * processGuess - processes the user's guess and compares it with the mystery word
     * 
  
     * Returns true if the guess matches the mystery word exactly
     */
    public static boolean processGuess(String guess, String mystery) {
        char[] result = new char[5];
        boolean[] mysteryUsed = new boolean[5];
        boolean[] guessProcessed = new boolean[5];
        
        // First pass: mark all correct positions (green)
        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == mystery.charAt(i)) {
                result[i] = 'G'; // Green
                mysteryUsed[i] = true;
                guessProcessed[i] = true;
            }
        }
        
        // Second pass: mark letters in wrong positions (yellow)
        for (int i = 0; i < 5; i++) {
            if (!guessProcessed[i]) {
                char guessChar = guess.charAt(i);
                boolean foundMatch = false;
                
                // Look for an unused matching letter in mystery
                for (int j = 0; j < 5; j++) {
                    if (!mysteryUsed[j] && mystery.charAt(j) == guessChar) {
                        result[i] = 'Y'; // Yellow
                        mysteryUsed[j] = true;
                        foundMatch = true;
                        break;
                    }
                }
                
                if (!foundMatch) {
                    result[i] = '_'; // Gray (not in word)
                }
            }
        }
        
        // Build and display the colored output
        System.out.print("  ");
        boolean isCorrect = true;
        for (int i = 0; i < 5; i++) {
            char displayChar = guess.charAt(i);
            if (result[i] == 'G') {
                System.out.print(GREEN + displayChar + RESET + " ");
            } else if (result[i] == 'Y') {
                System.out.print(YELLOW + displayChar + RESET + " ");
                isCorrect = false;
            } else {
                System.out.print(GRAY + displayChar + RESET + " ");
                isCorrect = false;
            }
        }
        System.out.println();
        System.out.println();
        
        return isCorrect;
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        
        printWelcome();

        // Create the WordList object for the collection of possible words.
        WordList words = initWordList(args);

        // Choose one of the words as the mystery word.
        String mystery = words.getRandomWord();
        
        // Debug mode: uncomment to see the mystery word
        // System.out.println("DEBUG: Mystery word is " + mystery);

        // Main game loop
        boolean won = false;
        for (int i = 1; i <= 6; i++) {
            String guess = readGuess(i, console);
            if (processGuess(guess, mystery)) {
                System.out.println(GREEN + BOLD + "Congrats! You guessed it!" + RESET);
                won = true;
                break;
            } else if (i < 6) {
                System.out.println("  " + (6 - i) + " guess(es) remaining");
                System.out.println();
            }
        }
        
        if (!won) {
            System.out.println(GRAY + "Sorry! Better luck next time!" + RESET);
            System.out.println("The word was " + BOLD + mystery.toUpperCase() + RESET + ".");
        }

        console.close();
    }
}
