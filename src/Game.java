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

    public static void startGame() {
        myColor = readInput();
        if (myColor.equals("IB")){
            currentPlayer = ME;
        }else
            currentPlayer = OPPONENT;

        board = new Board(myColor);
        board.printBoard();
    }

    public static String readInput(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input.replaceAll("\\s+","");
    }

    public void playGame(){
        String move = null;
        while(!board.isGameOver()){

        }
    }

    public static void main(String[] args){
        startGame();
    }

}
