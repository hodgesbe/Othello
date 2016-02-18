import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by bHodges on 2/10/16.
 */

//Main game class
public class Game {
    public static final int ME = 1;
    public static final int OPPONENT = -1;
    static int currentPlayer = 0;
    static String myColor = null;
    static Board board;
    static ArrayList myMoves;

    public static void startGame() {
        myColor = readInput();
        if (myColor.equals("IB")){
            currentPlayer = ME;
        }else
            currentPlayer = OPPONENT;

        board = new Board(myColor);
        board.printBoard();
        myMoves = board.generateMoves(ME);
        System.out.println();
        for (int i = 0; i<myMoves.size(); i++){
            System.out.print("Move "+i+"is: "+myMoves.get(i)+", ");
        }
    }

    public static String readInput(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input.replaceAll("\\s+","").toUpperCase();
    }

    public static void main(String[] args){
        startGame();

    }

}
