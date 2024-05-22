# Wordle

# Wordle Game

## Overview

Welcome to the Wordle Game! This is a console-based implementation of a popular word-guessing game developed in Java. The objective of the game is to guess the mystery 5-letter English word within 6 attempts.

## Features

- **Interactive Console Interface**: Play the game in your terminal or command prompt.
- **Dynamic Game Board**: The game board updates in real-time as you make your guesses.
- **Win Detection**: The game automatically detects and announces the winner.
- **Draw Detection**: If you exhaust all attempts without guessing the word, the game ends.

## Requirements

- Java Development Kit (JDK) 8 or higher

## How to Run

1. **Clone the repository**:
    ```sh
    git clone https://github.com/yourusername/wordle-game.git
    cd wordle-game
    ```

2. **Compile the Java files**:
    ```sh
    javac Wordle.java WordList.java
    ```

3. **Run the game**:
    ```sh
    java Wordle
    ```

## How to Play

1. **Start the game**: Run the `Wordle` class.
2. **Guess the word**: Enter your guess for the 5-letter mystery word when prompted.
3. **Feedback**: 
   - Correct letters in the correct positions will be displayed as is.
   - Correct letters in the wrong positions will be displayed within brackets `[ ]`.
   - Incorrect letters will be displayed as underscores `_`.
4. **Win or Lose**: You have 6 attempts to guess the word correctly. The game will announce if you win or lose after your attempts.

## Example Gameplay

