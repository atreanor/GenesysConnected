package connect5;

// import Junit packages
import static org.junit.Assert.*;
import org.junit.Test;

/*
BoardTest is a unit testing class to test Board.java & its methods
 */
public class BoardTest {

    @Test
    public void testCheckHorizontal() {
        Board board = new Board(6,9);
        char[] testHorizontalRed = {'R','R', 'R', 'R','R','R', 'R', 'R', 'R'};
        char[] testHorizontalYellow = {'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y'};
        int i = 0;
        for (char disk: testHorizontalRed){
            board.addDisk(i, disk);
            i++;
        }
        int j = 0;
        for (char disk: testHorizontalYellow){
            board.addDisk(j, disk);
            j++;
        }
        board.printBoard();

        assertTrue(board.checkHorizontal(5, 'R'));
        assertTrue(board.checkHorizontal(4,'Y'));

    }

    @Test
    public void testCheckVertical() {
        Board board1 = new Board(6,9);
        char[] testVerticalRed = {'R','R', 'R', 'R','R','R'};
        char[] testVerticalYellow = {'Y', 'Y', 'Y', 'Y', 'Y', 'Y'};
        int i = 0;
        for (char disk: testVerticalRed){
            board1.addDisk(i, disk);
        }
        int j = 1;
        for (char disk: testVerticalYellow){
            board1.addDisk(j, disk);
        }
        board1.printBoard();
        assertTrue(board1.checkVertical(0, 'R'));
        assertTrue(board1.checkVertical(1, 'Y'));

    }

    @Test
    public void testCheckDiagonal() {
        Board board2 = new Board(6,9);
        char colour = 'R';
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++){
                board2.addDisk(j, colour);
                if (colour == 'R') {
                    colour = 'Y';
                } else {
                    colour = 'R';
                }
            }
        }
        assertTrue(board2.checkDiagonal(2, 3, 'R'));
        assertTrue(board2.checkDiagonal(0, 1, 'R'));
        assertTrue(board2.checkDiagonal(3, 5, 'Y'));
        assertTrue(board2.checkDiagonal(0, 0, 'Y'));
        board2.printBoard();

    }

    @Test
    public void testIsValidMove(){
        Board board4 = new Board(6, 9);
        assertTrue(board4.isValidMove(0));
        assertTrue(board4.isValidMove(1));
        assertTrue(board4.isValidMove(2));
        assertTrue(board4.isValidMove(3));
        assertTrue(board4.isValidMove(4));
        assertTrue(board4.isValidMove(5));
        assertTrue(board4.isValidMove(6));
        assertTrue(board4.isValidMove(7));
        assertTrue(board4.isValidMove(8));
    }
}
