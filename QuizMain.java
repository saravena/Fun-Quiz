import java.util.*;
import java.io.*;

public class QuizMain {
	public static void main(String[] args) throws IOException {

		// initializes variables
		String questionIn;
		int numberAns;
		String[] answersIn = new String[1];
		int numRightCorrect;
		int answerAttempt;
		int answerCorrect;

		Question question = new Question();
		Scanner inputScan = new Scanner(System.in);

		System.out.println("Welcome to the Quiz Program!\n");
		System.out.println("Please choose a quiz:");
		System.out.println("(Enter the text file below)");
		System.out.println("\t- Sample.txt");
		System.out.print(" >>  ");

		// takes in user input for specified text file
		String enterTxt = inputScan.nextLine();

		// checks to see if the file entered by the user exists
		File file = new File(enterTxt);
		while(!file.exists()){
				System.out.println("\nThis file does not exist. Please enter an appropriate file name.");
				System.out.println("(Do not forget to add '.txt' after each file name)");

				System.out.print(" >>  ");
				enterTxt = inputScan.nextLine();
				file = new File(enterTxt);
		} // end while
		
		System.out.println("\nYou chose " + enterTxt + "   Good Luck!");
		System.out.println("(Please answer the question with the number corresponding to the answer.)");
		System.out.println();

		// opens the file
		Scanner inputFile = new Scanner(file);
		ArrayList<Question> list = new ArrayList<Question>();

		//reads until end of file and puts it into an arraylist
		while(inputFile.hasNextLine()) {
			question = new Question();
			// Question
			questionIn = inputFile.nextLine();
			question.setQuestion(questionIn);
			// Number of Answers
			numberAns = inputFile.nextInt();
			question.setNumAnswers(numberAns); 
			// Reads in blank new line charcater
			inputFile.nextLine();
			// takes the different options and puts it into an array
			answersIn = new String[question.getNumAnswers()];
			for (int i = 0; i < question.getNumAnswers(); i++) {
				answersIn[i] = inputFile.nextLine();
			}
			question.setOptions(answersIn);
			// number of the correct answer
			numRightCorrect = inputFile.nextInt();
			question.setRightAns(numRightCorrect);
			// number of attempts on the question
			answerAttempt = inputFile.nextInt();
			question.setNumAttempts(answerAttempt);
			// number of correct attempts on question
			answerCorrect = inputFile.nextInt();
			question.setNumCorrect(answerCorrect);
			// reads in blank new line character
			inputFile.nextLine();
			// adds element into array list
			list.add(question);
		} // end while

		//  closes the file
		inputFile.close();


		int userAnswer = 0;
		// Displays questions to user and have them answer
		for (int idx = 0; idx < list.size(); idx++) {
			inputScan = new Scanner(System.in);
			question = list.get(idx);

			System.out.println(idx + ". " + list.get(idx).getQuestion());

			System.out.println("Answers:");
			for (int m = 0; m < list.get(idx).getNumAnswers(); m++) {
				System.out.println("\t(" + m + ")\t" + list.get(idx).getOptions(m));
			}
			System.out.println(); 

			// takes in user input
			System.out.print("Your answer?  >>    ");
			userAnswer = inputScan.nextInt();
			question.setUserAnswer(userAnswer);

			// checks if user input is valid
			while (userAnswer < 0 || userAnswer > list.get(idx).getNumAnswers() - 1) {
				System.out.println("Error: That is not a vald answer. Please re-enter.");
				inputScan.reset();
				System.out.print("Your answer?  >>    ");
				userAnswer = inputScan.nextInt();
				question.setUserAnswer(userAnswer);
			} // end while

			list.get(idx).numAnsAttempt();
		}

		System.out.println();
		System.out.println("Thank you for taking this quiz!\n");
		System.out.println("\nHere are your results:");

		// Displays the results of the quiz
		int correctAnswer = 0;
		int userInputAns = 0;
		int countRight = 0;
		int countWrong = 0;

		for(int k = 0; k < list.size(); k++) {
			System.out.println("Question: " + list.get(k).getQuestion());
			correctAnswer = list.get(k).getRightAns();
			System.out.println("Answer: " + list.get(k).getOptions(correctAnswer));
			userInputAns = list.get(k).getUserAnswer();
			System.out.println("Player Guess: " + list.get(k).getOptions(userInputAns));

			if (userInputAns == correctAnswer) {
				System.out.println("\tResult: CORRECT!! Great job!\n");
				countRight++;
				list.get(k).numAnsCorrect();
			} else {
				System.out.println("\tResult: INCORRECT!  Sorry..\n");
				countWrong++;
			}
		}

		// calculates quiz score
		double score = (double)countRight/list.size();

		// displays overall performance
		System.out.println();
		System.out.println("Your overall performance: ");
		System.out.println("\tRight: " + countRight);
		System.out.println("\tWrong: " + countWrong);
		System.out.println("\tPct: " + score);

		// displays the cumulative statistics of the quiz
		System.out.println();
		System.out.println();
		System.out.println("Here are some cumulative statistics:");

		double highPercent = 0.0;
		double lowPercent = 100.0;

		int highQ = 0;
		int lowQ = 0;

		for(int n = 0; n < list.size(); n++) {
			System.out.println("Question: " + list.get(n).getQuestion());
			System.out.println("\tTimes Tried: " + list.get(n).getNumAttempts());
			System.out.println("\tTimes Correct: " + list.get(n).getNumCorrect());
			// calculates the the correct statistics
			double stat = ((double)list.get(n).getNumCorrect()/list.get(n).getNumAttempts()) * 100;
			System.out.printf("\tPercent Correct: %.1f", stat);
			System.out.println("%");
			System.out.println();

			// figures out the easiest and hardest question
			if(stat > highPercent) {
				highPercent = stat;
				highQ = n;
			}
			if(stat < lowPercent) {
				lowPercent = stat;
				lowQ = n;
			}
		}

		// displays easiest and hardest queston
		System.out.println();
		System.out.println("Easiest Question:");
		System.out.println("Question: " + list.get(highQ).getQuestion());
		System.out.println("\tTimes Tried: " + list.get(highQ).getNumAttempts());
		System.out.println("\tTimes Correct: " + list.get(highQ).getNumCorrect());
		System.out.printf("\tPercent Correct: %.1f", highPercent);
		System.out.println("%");
		System.out.println();
		System.out.println("Hardest Question:");
		System.out.println("Question: " + list.get(lowQ).getQuestion());
		System.out.println("\tTimes Tried: " + list.get(lowQ).getNumAttempts());
		System.out.println("\tTimes Correct: " + list.get(lowQ).getNumCorrect());
		System.out.printf("\tPercent Correct: %.1f", lowPercent);
		System.out.println("%");
		
		// updates the text file
		PrintWriter outFile = new PrintWriter(enterTxt);
		for (int y = 0; y < list.size(); y++) {
			outFile.println(list.get(y).getQuestion());
			outFile.println(list.get(y).getNumAnswers());
			for (int z = 0; z < list.get(y).getNumAnswers(); z++){
				outFile.println(list.get(y).getOptions(z));
			}
			outFile.println(list.get(y).getRightAns());
			outFile.println(list.get(y).getNumAttempts());
			outFile.println(list.get(y).getNumCorrect());
		}
		outFile.close();

	} // end main
} // end class QuizMain