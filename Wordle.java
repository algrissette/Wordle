/*
 * Wordle.java 
 * 
 * A console-based implementation of a popular word-guessing game
 * 
 * starter code: Computer Science 112 staff (cs112-staff@cs.bu.edu)
 *
 * completed by: 
 */

import java.util.*;

public class Wordle {
    // the name of a file containing a collection of English words, one word per line
    public static final String WORD_FILE = "words.txt";

    /*
     * printWelcome - prints the message that greets the user at the beginning of the game
     */  
    public static void printWelcome() {
        System.out.println();   
        System.out.println("Welcome to Wordle!");
        System.out.println("The mystery word is a 5-letter English word.");
        System.out.println("You have 6 chances to guess it.");
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
     * includes - checks if the character c is present in the string s
     */
    public static boolean includes(String s, char c) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                return true;
            }
        }
        return false;
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
     * numOccur - counts the occurrences of the character c in the string s
     */
    public static int numOccur(char c, String s) {
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                counter++;
            }
        }
        return counter;
    }

    /*
     * numInSamePosn - counts the number of times the character c occurs in the same position in both strings s1 and s2
     */
    public static int numInSamePosn(char c, String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == c && s2.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    /*
     * isValidGuess - takes an arbitrary string guess and returns true
     * if it is a valid guess for Wordle, and false otherwise
     */
    public static boolean isValidGuess(String guess) {
        if (guess.length() != 5) {
            System.out.println("Your guess must be 5 letters long.");
            return false;
        } else if (!isAlpha(guess)) {
            System.out.println("Your guess must only contain letters of the alphabet.");
            return false;
        } else {
            return true;
        }
    }

    /*
     * processGuess - processes the user's guess and compares it with the mystery word
     */
    public static boolean processGuess(String guess, String mystery) {
        StringBuilder result = new StringBuilder();
        boolean isCorrect = true;

        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) != mystery.charAt(i)) {
                isCorrect = false;
                if (includes(mystery, guess.charAt(i))) {
                    result.append("[").append(guess.charAt(i)).append("] ");
                } else {
                    result.append("_ ");
                }
            } else {
                result.append(guess.charAt(i)).append(" ");
            }
        }

        System.out.println("  " + result.toString().trim());
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

        // Implement the rest of the main method below.
        for (int i = 1; i <= 6; i++) {
            String guess = readGuess(i, console);
            if (processGuess(guess, mystery)) {
                System.out.println("Congrats! You guessed it!");
                console.close();
                return;
            }
        }
        System.out.println("Sorry! Better luck next time!");
        System.out.println("The word was " + mystery + ".");

        console.close();
    }
}
