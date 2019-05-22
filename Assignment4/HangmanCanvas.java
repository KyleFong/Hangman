/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

	private static final int WIDTH = 400; // size of canvas
	private static final int HEIGHT = 500;

	private double startX = WIDTH/2 - BEAM_LENGTH; // sets starting positions
	private double startY = HEIGHT/2 + SCAFFOLD_HEIGHT/2 - 50;
	private double startBodyX = startX + BEAM_LENGTH;
	private double startBodyY = startY - SCAFFOLD_HEIGHT + ROPE_LENGTH;

	private GLabel textDisplayLabel = null; //creates different labels
	private GLabel wrongLabel = null;
	private String wrongGuess = "";
	private int guessCounter = 0; // sets up guess counter

/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* You fill this in */
		removeAll();
		buildScaffold();
	}

	private void buildScaffold() { // creates the scaffold
		GLine scaffold = new GLine(startX, startY, startX, startY-SCAFFOLD_HEIGHT);
		GLine beam = new GLine(startX, startY - SCAFFOLD_HEIGHT, startX+BEAM_LENGTH, startY - SCAFFOLD_HEIGHT);
		GLine rope = new GLine(startX + BEAM_LENGTH, startY - SCAFFOLD_HEIGHT, startX + BEAM_LENGTH, startY - SCAFFOLD_HEIGHT+ROPE_LENGTH);
		add(scaffold);
		add(beam);
		add(rope);
	}
/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) { // creates and updates the display label
		/* You fill this in */

		if(textDisplayLabel != null) {
			remove(textDisplayLabel);
		}

		textDisplayLabel = new GLabel(word, startX, startY + 60);
		textDisplayLabel.setFont("Courier-30");
		add(textDisplayLabel);

	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) { // adds and updates wrong letters
		/* You fill this in */
		if(wrongLabel != null) {
			remove(wrongLabel);
		}
		wrongGuess += letter;
		guessCounter++;
		buildBody();
		wrongLabel = new GLabel(wrongGuess, startX, startY + 80);
		wrongLabel.setFont("Courier-25");

		add(wrongLabel);
	}

	private void buildBody() { // creates another body part for each guess label
		switch(guessCounter) {
			case 0: break;
			case 1: GOval head = new GOval(startBodyX - HEAD_RADIUS, startBodyY, 2*HEAD_RADIUS, 2*HEAD_RADIUS);
			add(head);
			break;
			case 2: GLine body = new GLine(startBodyX, startBodyY + 2*HEAD_RADIUS, startBodyX, startBodyY + 2*HEAD_RADIUS + BODY_LENGTH);
			add(body);
			break;
			case 3: GLine leftArm = new GLine(startBodyX, startBodyY + 2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD, startBodyX-UPPER_ARM_LENGTH, startBodyY + 2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD);
			add(leftArm);
			GLine leftArm2 = new GLine(startBodyX - UPPER_ARM_LENGTH, startBodyY + 2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD, startBodyX - UPPER_ARM_LENGTH, startBodyY+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
			add(leftArm2);
			break;
			case 4: GLine rightArm = new GLine(startBodyX, startBodyY + 2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD, startBodyX+UPPER_ARM_LENGTH, startBodyY + 2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD);
				add(rightArm);
				GLine rightArm2 = new GLine(startBodyX + UPPER_ARM_LENGTH, startBodyY + 2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD, startBodyX + UPPER_ARM_LENGTH, startBodyY+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
				add(rightArm2);
				break;
			case 5: GLine leftLeg = new GLine(startBodyX, startBodyY + 2*HEAD_RADIUS + BODY_LENGTH, startBodyX-HIP_WIDTH, startBodyY + 2*HEAD_RADIUS + BODY_LENGTH);
				add(leftLeg);
				GLine leftLeg2 = new GLine(startBodyX-HIP_WIDTH, startBodyY + 2*HEAD_RADIUS + BODY_LENGTH, startBodyX-HIP_WIDTH, startBodyY + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
				add(leftLeg2);
				break;
			case 6:GLine rightLeg = new GLine(startBodyX, startBodyY + 2*HEAD_RADIUS + BODY_LENGTH, startBodyX+HIP_WIDTH, startBodyY + 2*HEAD_RADIUS + BODY_LENGTH);
				add(rightLeg);
				GLine rightLeg2 = new GLine(startBodyX+HIP_WIDTH, startBodyY + 2*HEAD_RADIUS + BODY_LENGTH, startBodyX+HIP_WIDTH, startBodyY + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
				add(rightLeg2);
				break;
			case 7: GLine leftFoot = new GLine(startBodyX - HIP_WIDTH, startBodyY + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, startBodyX - HIP_WIDTH - FOOT_LENGTH, startBodyY + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
				add(leftFoot);
				break;
			case 8: GLine rightFoot = new GLine(startBodyX + HIP_WIDTH, startBodyY + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, startBodyX + HIP_WIDTH + FOOT_LENGTH, startBodyY + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
				add(rightFoot);
				break;
			default: break;
		}
	}



}
