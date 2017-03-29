import java.util.*;
import java.io.*;

public class Question {
	// Instance Variables
	private String question;
	private int numAns;
	private String[] answers;
	private int rightCorrect;
	private int ansAttempt;
	private int ansCorrect;
	private int userInput;


	// Constructors
	public Question() {
		question = "";
		numAns = 0;
		answers = new String[1];
		rightCorrect = 0;
		ansAttempt = 0;
		ansCorrect = 0;
		userInput = 0;
	} // end Question()


	// Mutators
	public void setQuestion(String setQuest) {
		question = setQuest;
	} // end setQuestion

	public void setNumAnswers(int setNumAns) {
		numAns = setNumAns;
	} // end setNumAnswers

	public void setOptions(String[] numOptions) {
		answers = new String[numOptions.length];
		for (int i = 0; i < numOptions.length; i++) {
			answers[i] = numOptions[i];
		}
	} // end setOptions

	public void setRightAns(int rightAnswer) {
		rightCorrect = rightAnswer;
	} // end setRightAns

	public void setNumAttempts(int numAttempts) {
		ansAttempt = numAttempts;
	} // end setNumAttempts

	public void setNumCorrect(int numCorrect) {
		ansCorrect = numCorrect;
	} // end setNumCorrect
	// keeps count and increments number of attempts on the question by 1
	public int numAnsAttempt() {
		ansAttempt++;
		return ansAttempt;
	} // end numAnsAttempt()
	// keeps count and increments number of attempts correct on the question by 1
	public int numAnsCorrect() {
		ansCorrect++;
		return ansCorrect;
	} // end numAnsCorrect()

	public void setUserAnswer(int input) {
		userInput = input;
	} // end userAnswer


	// Accessors
	public String getQuestion() {
		return question;
	} // end getQuestion()

	public int getNumAnswers() {
		return numAns;
	} // end getAnswer()

	public String getOptions(int num) {
		return answers[num];
	} // end getOptions()

	public int getRightAns() {
		return rightCorrect;
	} // end getRightAns()

	public int getNumAttempts() {
		return ansAttempt;
	} // end getNumAttempts()

	public int getNumCorrect() {
		return ansCorrect;
	} // end getNumCorrect

	public int getUserAnswer() {
		return userInput;
	}

} // end class Question