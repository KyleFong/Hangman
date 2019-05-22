/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {


    private RandomGenerator rgen = RandomGenerator.getInstance(); // creates random number generator
    private HangmanLexicon list = new HangmanLexicon(); // creates word list
    private HangmanCanvas canvas; // creates canvas
    private String word; // creates variables
    private String guessWord;
    private int guessesLeft = 8;

    public void init() { // adds the canvas
        canvas = new HangmanCanvas();
        add(canvas);
    }

    public void run() { // sets up and runs the game
		/* You fill this in */
        setupGame();
        canvas.reset();
        canvas.displayWord(guessWord);
        runGame();

	}
	public void setupGame() {
        println("Welcome to Hangman!");
        word = wordPicker(); // picks the word
        guessWord = firstUpdate(); // adds dashes to the console
        guessUpdate(); // updates the word
    }

    public void runGame() {
        while(true) {
            String guess = readLine("Your guess: "); // asks for the guess
            guess = guess.toUpperCase(); // puts the guess to upper case

            while(validGuess(guess) == false) { // if guess is valid
                guess = readLine("Your guess: "); // asks for next guess
                guess = guess.toUpperCase();
            }
            checkLetter(guess); // checks if letter is correct
            if(checkWinLoss() == false){ //check if you have won or lost
                wordUpdate(); // updates word
                guessUpdate(); // updates guess
            } else {
                break; // if you have won or lost, break
            }
        }
    }

    private boolean checkWinLoss() {
        if(guessesLeft == 0) { // if you have no guesses left
            println("You're completely hung.");
            println("The word was: " + word); // show the word
            println("You lose.");
            return true;
        } else if(guessWord.equals(word)){ // if word has been guessed
            println("You guessed the word: " + word);
            println("You win");
            return true;
        }
        return false; // if neither is true, return false
    }

    private void wordUpdate() {
        println("The word now looks like this: " + guessWord); // prints this line
    }

    private void checkLetter(String guess) {
        if(word.indexOf(guess) == -1) { // if letter is not in word
            guessesLeft--; // take off a guess
            println("There are no " + guess + "'s in the word."); // tell the player
            canvas.noteIncorrectGuess(guess.charAt(0)); // adds incorrect letter to the canvas
        } else {
            println("That guess is correct.");
            for(int i = 0; i < word.length(); i++) { //loops through the words
                if(word.charAt(i) == guess.charAt(0) && i != 0) { // if letter isn't the first
                    guessWord = guessWord.substring(0, i) + guess + guessWord.substring(i + 1); // add in the letter
                } else if(word.charAt(i) == guess.charAt(0) && i == 0) { // if it is the first
                    guessWord = guess + guessWord.substring(i+1); // add it to the beginning
                }
            }
            canvas.displayWord(guessWord); // adds correct word to the canvas
        }
    }

    private boolean validGuess(String guess) {
        if(guess.length() > 1 || guess.length() < 1) { // if the guess is more than one letter
            return false;
        } else if(Character.isDigit(guess.charAt(0))){ // if it is a letter
            return false;
        }
        return true;
    }

    public String wordPicker() {
        int wordIndex = rgen.nextInt(0, list.getWordCount()-1); // random number generator chooses a word
        String wordPicked = list.getWord(wordIndex);
        return wordPicked; // return word
    }

    private String firstUpdate() {
        String result = "";

        for(int i = 0; i < word.length(); i++) { // fills the dashes where letters eventually show up
            result = result + "-";
        }
        println("The word now looks like this " + result);
        return result;
    }

    private void guessUpdate() {
        println("You have " + guessesLeft + " guessses left"); // prints this line
    }

}
