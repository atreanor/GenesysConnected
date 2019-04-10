package connect5;

/*
* Board class contains class constructor to create new Board & methods to print board state, 
* check if move is valid, add a disk to board, check board for a horizontal, vertical & 
* diagonal winner.
*/
public class Board {
	
	// initialize class variables
	private int disks = 0; // total number of disks per game counter
	private boolean winner = false;
	private char[][] nb; // two dimensional array to hold 'Y' & 'R' moves on board 
	private int rowPos; // variable to hold the row position of disk entry
	private boolean move; // boolean variable to check if move is valid
	// connect5.Player currentPlayer; // variable holder for current player
	
  /*
  Class constructor creates instance of Board from integer parameters.
   */
  public Board(int rows, int cols) {
      // create board with input parameters
      this.nb = new char[rows][cols];
  }


  /*
  Method to print current state of board to console
   */
  public void printBoard() {
      // nested loop iterates over two dimensional array & prints [R], [Y] or [ ].
      for (int i = 0; i < nb.length; i++) {
          for (int j = 0; j < nb[i].length; j++) {
              if (nb[i][j] == 'R') {
                  System.out.print("[" + "R" + "]");
              } else if (nb[i][j] == 'Y') {
                  System.out.print("[" + "Y" + "]");
              } else {
                  System.out.print("[" + " " + "]");
              }
          }
          // create a newline
          System.out.print("\n");
      }
      // print column numbers under board in console
      System.out.println(" 0  1  2  3  4  5  6  7  8 ");
  }


  /*
  isValidMove method checks whether the selection made by user is a valid move, either within range (0-8)
  or column is not full. Returns boolean - valid move: true, invalid move: false.
   */
  public boolean isValidMove(int column) {
      if (column >= 0 && column <=8 && nb[0][column]!= 'R' && nb[0][column]!= 'Y' && disks < 54) {
          move = true;
          System.out.println("VALID MOVE");
      } else {
          move = false;
      }
      return move;
  }
	
  /*
  Method to add either 'R' or 'Y' disk by column into the lowest available row on grid
   */
  public int addDisk(int column, char colour) {
  
      if (column >= 0 && column <= 8 && disks <= 57) {
          if (nb[5][column] != 'R' && nb[5][column] != 'Y') {
              nb[5][column] = colour; rowPos = 5;
          } else if (nb[4][column] != 'R' && nb[4][column] != 'Y') {
              nb[4][column] = colour; rowPos = 4;
          } else if (nb[3][column] != 'R' && nb[3][column] != 'Y') {
              nb[3][column] = colour; rowPos = 3;
          } else if (nb[2][column] != 'R' && nb[2][column] != 'Y') {
              nb[2][column] = colour; rowPos = 2;
          } else if (nb[1][column] != 'R' && nb[1][column] != 'Y') {
              nb[1][column] = colour; rowPos = 1;
          } else if (nb[0][column] != 'R' && nb[0][column] != 'Y') {
              nb[0][column] = colour; rowPos = 0;
          }
      }

      // increment the number of disks entered to board
      disks++;
      // return rowPos to help check board state for winner
      return rowPos;
  }
  
  /*
  Method to check state of board for a horizontal connect 5
   */
  public boolean checkHorizontal(int rowPos, char player){
      int horizontalCount = 0;
      for (int i = 0; i < nb[rowPos].length; i++) {
          if (nb[rowPos][i] == player) {
              horizontalCount++;
              if (horizontalCount >= 5) {
                  winner=true;
              }
          } else {
              horizontalCount = 0;
          }
      }
      return winner;
  }
  
  /*
  Method to check state of board for Vertical 5 in a row
   */
  public boolean checkVertical(int col, char player){
      if (nb[5][col] == player && nb[4][col] == player && nb[3][col] == player && nb[2][col] == player
              && nb[1][col] == player){
          winner = true;
      } else if (nb[0][col] == player && nb[1][col] == player && nb[2][col] == player && nb[3][col] == player
              && nb[4][col] == player){
          winner = true;
      }
      return winner;
  }

  /*
  Method to check state of board for Diagonal connect 5 in both directions - left-to-right & right-to-left
   */
  public boolean checkDiagonal(int rowPos, int col, char player){

      // ** check diagonal from lower left to upper right **
      // variables to used to hold row & column position, & the distance of initial move
      int R = rowPos,  C = col, diff;
      int count1 = 0; int count2 = 0;

      // calculate diff - the maximum allowable move to the lower left
      if (5 - R < col + 1){
          diff = 5 - R;
      } else {
          diff = col + 1;
      }
      // modify row & column by diff to move position diagonally lower to the left
      R = R + diff; C = C - diff;

      // loop moves diagonally from left-to-right through players choice, incrementing counter if disks are connected
      while(R > 0 && R <= 5 && C >=0 && C < 8) {
          if(nb[R][C]==player){
              count1++;
              R--;C++;
          } else {
              R--;C++;
          }
      }

      // ** check diagonal from lower right to upper left **
      // calculate diff - the maximum allowable move to the lower right
      if (5 - rowPos < 8 - col){
          diff = 5 - rowPos;
      } else {
      diff = 8 - col;
      }

      // modify row & column by minimum difference to move position diagonally lower to the right
      R = rowPos + diff; C = col + diff;

      // loop moves diagonally from right-to-left through players choice, incrementing count2 if disks are connected
      while (R > 0 && R <= 5 && C > 0 && C < 8){
          if(nb[R][C]==player){
              count2++;
              R--; C--;
          } else {
              R--; C--;
          }
      }

      // check count1 & count2 for connect 5 winner
      if (count1 >= 5 || count2 >= 5){
          winner=true;
      }
      // return result
      return winner;
  }
}
