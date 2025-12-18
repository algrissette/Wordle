# Wordle - Console Implementation

A fully-featured console-based implementation of the popular word-guessing game Wordle, built in Java with proper color-coding and authentic game mechanics.

## 🎮 Game Overview

Wordle is a word puzzle game where players have 6 attempts to guess a secret 5-letter word. After each guess, the game provides color-coded feedback:
- **🟩 Green**: Letter is correct and in the right position
- **🟨 Yellow**: Letter is in the word but in the wrong position  
- **⬜ Gray**: Letter is not in the word at all

## ✨ Features

- **Authentic Wordle mechanics** - Correctly handles duplicate letters following official Wordle rules
- **Color-coded terminal output** - Uses ANSI color codes for visual feedback
- **Input validation** - Ensures guesses are exactly 5 letters and contain only alphabetic characters
- **Randomized word selection** - Draws from a dictionary of valid English words
- **Seeded random option** - Allows reproducible games for testing or challenges
- **User-friendly interface** - Clear prompts, helpful error messages, and guess tracking

## 🔧 Technical Highlights

### Duplicate Letter Handling (Key Algorithm)

The game implements a two-pass algorithm to correctly handle words with duplicate letters:

1. **First Pass**: Mark all letters in correct positions (green) and flag them as "used"
2. **Second Pass**: For remaining letters, mark as yellow only if an unused matching letter exists in the mystery word

**Example**: If the mystery word is "ROBOT" and you guess "FLOOR":
- Position 3: O → Green (correct position)
- Position 3: O → Gray (already matched, no more O's available)

This ensures each letter in the mystery word can only be matched once, preventing misleading feedback.

### Code Structure

```
Wordle.java          # Main game logic and UI
WordList.java        # Word selection and management (not shown)
words.txt            # Dictionary of valid 5-letter words
```

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- A terminal that supports ANSI color codes (most modern terminals)

### Running the Game

1. Compile the program:
```bash
javac Wordle.java WordList.java
```

2. Run with a random word:
```bash
java Wordle
```

3. Run with a specific seed for reproducible games:
```bash
java Wordle 12345
```

### Gameplay Example

```
Welcome to Wordle!
The mystery word is a 5-letter English word.
You have 6 chances to guess it.

Color Guide:
  GREEN = Correct letter in correct position
  YELLOW = Correct letter in wrong position
  GRAY = Letter not in word

guess 1: crane
  c r a n e 

  5 guess(es) remaining

guess 2: stole
  s t o l e 

  4 guess(es) remaining

guess 3: phone
  p h o n e 

Congrats! You guessed it!
```

## 🎯 What I Learned

Through building this project, I gained experience with:

- **Algorithm design**: Implementing the duplicate letter matching algorithm required careful state tracking and two-pass processing
- **Input validation**: Building robust validation for user input with helpful error messages
- **Terminal UI**: Working with ANSI escape codes to create a colored console interface
- **Code organization**: Structuring a complete game with clean separation of concerns
- **Edge case handling**: Thoroughly testing scenarios like duplicate letters, invalid input, and win/loss conditions

## 🐛 Known Issues & Future Enhancements

### Potential Improvements
- Add dictionary validation to reject non-words
- Implement hard mode (must use revealed hints)
- Track win/loss statistics across games
- Add a "replay" option without restarting
- Support different word lengths
- Add keyboard tracking display showing used letters

## 📝 License

This project was completed as coursework for Computer Science 112 at Boston University.

## 👤 Author

Created as part of my portfolio to demonstrate problem-solving skills, algorithm implementation, and clean code practices in Java.

---

**Note**: This implementation focuses on accurate game mechanics and code quality. The most important feature is the correct handling of duplicate letters, which many Wordle clones get wrong.
