import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by bHodges on 2/10/16.
 */

//Main game class
public class Game {
    final int ME = 1;
    final int OPPONENT = -1;
    int currentPlayer = 0;
    String initColor = null;
    char myColor;
    char oppColor;
    Board board;
    ArrayList myMoves;

    public Game() {

        initColor = readInput();
        if (initColor.equals("IB")){
            currentPlayer = ME;
            myColor = 'B';
            oppColor = 'W';
        }else{
            currentPlayer = OPPONENT;
            myColor = 'W';
            oppColor = 'B';
        }
        board = new Board(initColor);
        board.printBoard();
    }

    private void playGame(){

        String move;
        while (!board.gameOver){
            //if the current player is me, take move from move list and play it
            if (currentPlayer == ME){
                board.applyMove(myColor+board.generateMoves(ME).get(0).toString());
                board.printBoard();
                currentPlayer = OPPONENT;
            }else{
                board.applyMove(readInput());
                board.printBoard();
                currentPlayer = ME;
            }
        }
    }

    public static String readInput(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input.replaceAll("\\s+","").toUpperCase();
    }

    public static void main(String[] args){
        Game testGame = new Game();
        testGame.playGame();

    }

}
