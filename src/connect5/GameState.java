package connect5;
// import Scanner class
import java.util.Scanner;

/*
 * GameState class controls the state of Connect 5 game
 */
public class GameState {
	// variables to hold player names
	private String player1, player2;
	//private boolean winner;
	private boolean player = true;
	private int disks = 0;
	private int row;

	/*
	Main Method - program entry point
	 */
	public static void main(String[] args) {
		// reference Board
		//connect5.Board board = new Board();
		// create new instance of GameState & print rules to console
		GameState game = new GameState();
		System.out.println("********************* Welcome to Connect5Server by Alan Treanor **************************\n" +
				"** 2 player game, enter your name when prompted. Turns are taken round about            **\n" +
				"** starting with player 1. Simply select the column you wish to drop your disk in.      **\n" +
				"** Match 5 in a row in a horizontal, vertical, or diagonal line to win.                 **\n" +
				"******************************************************************************************");

		// create new Scanner object for console input
		Scanner input = new Scanner(System.in);

		// prompt users to enter names & assign input to variables
		System.out.println("Player 1 - enter your name: ");
		game.player1 = input.next();
		System.out.println("Player 2 - enter your name: ");
		game.player2 = input.next();

		// print player colours to console, create new instance of Board & print current state to console
		System.out.println(game.player1 + " - you are RED or 'R', " + game.player2 + " you are YELLOW or 'Y'");
	 	Board board = new Board(6,9);
	 	board.printBoard();
	    

		// while loop continues until 56 disks are entered or a winner is defined
		while (game.disks < 54){

			// variables to hold disk colour & column choice
			char colour;
			int column;

			if (game.player) {
				System.out.println(game.player1 + " enter the number column to drop your selection ( 0 - 8) : ");
				game.player = false;
				colour = 'R';
				column = input.nextInt();
			} else {
				System.out.println(game.player2 + " enter the number column to drop your selection ( 0 - 8) : ");
				game.player = true;
				colour = 'Y';
				column = input.nextInt();
			}

			// if column entry is a valid move & number of disks > 8 - add disk to board & check
			// for horizontal, vertical & diagonal connect 5. Break loop for winner!
			if (board.isValidMove(column)) {

				// increment number of valid disk entries
				game.disks++;

				if (game.disks > 8 ) {

					// add disk to board
					game.row = board.addDisk(column, colour);

					// print board to console
					board.printBoard();

					// check for horizontal, vertical & diagonal connect 5 - break & print result to console for winner!
					if (board.checkHorizontal(game.row, colour )) {
						if (colour == 'R') {
							System.out.println("Congratulations " + game.player1 + " has won with a horizontal connect 5.");
							break;
						} else if (colour == 'Y') {
							System.out.println("Congratulations " + game.player2 + " has won with a horizontal connect 5.");
							break;
						}
					} else if (board.checkVertical(column, colour)) {
						if (colour == 'R') {
							System.out.println("Congratulations " + game.player1 + " has won with a vertical connect 5.");
							break;
						} else if (colour == 'Y') {
							System.out.println("Congratulations " + game.player2 + " has won with a vertical connect 5.");
							break;
						}
					} else if (board.checkDiagonal(game.row, column, colour)) {
						if (colour == 'R') {
							System.out.println("Congratulations " + game.player1 + " has won with a diagonal connect 5.");
							break;
						} else if (colour == 'Y') {
							System.out.println("Congratulations " + game.player2 + " has won with a diagonal connect 5.");
							break;
						}
					}
            		              		              		  
				} else {
					// else < 8 disks, no requirement for connect 5 check. Add disk to board & print board to console
					board.addDisk(column, colour);
					board.printBoard();
				}
            		  
			}	else {
				// print invalid move to console, change boolean game.player so current player gets to chose again
				System.out.println("INVALID MOVE!!");
				if (game.player) {
					game.player = false;
				} else {
					game.player = true;
				}
			}
	          
		}

		// check if game is tie, print result to console
		if (game.disks > 53){
			System.out.println("It's a tie, no winner this time.");
		}
	    // print end of game message to console
		System.out.println("Game Over!! Well played.");

	    // close Scanner input object
		input.close();

	}

}
