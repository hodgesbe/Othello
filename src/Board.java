import java.util.List;

/**
 * Created by bHodges on 2/10/16.
 */

//Board Class
public class Board {

    public static final int BORDER = -2;
    public static final int EMPTY = 0;
    int[][] board = new int[10][10];
    boolean gameOver = false;

    public Board(String color) {
        initBoard(color);
    }

    private void initBoard(String color){
        int black = 0;
        int white = 0;
        if (color.equals("Black")){
            black = 1;
            white = -1;
            System.out.println("R B");
        }else{
            black = -1;
            white = 1;
            System.out.println("R W");
        }

        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board.length; j++){
                if (i == 0 || i == 9){
                    board[i][j] = BORDER;
                }else if (j == 0 || j == 9){
                    board[i][j] = BORDER;
                }else
                    board[i][j] = EMPTY;
            }
        }

        board[4][4] = white;
        board[4][5] = black;
        board[5][4] = black;
        board[5][5] = white;
    }

    public void printBoard(){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                if (board[i][j]>-1){
                    System.out.print(" "+board[i][j]+" ");
                }else
                    System.out.print(board[i][j]+" ");
            }
            System.out.print("\n");
        }
    }


    public boolean isGameOver(){
        if (gameOver){
            return true;
        }else
            return false;
    }
}
