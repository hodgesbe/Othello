import java.util.HashMap;
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
        if (color.equals("IB")){
            black = 1;
            white = -1;
            System.out.println("R B");
        }else if(color.equals("IW")){
            black = -1;
            white = 1;
            System.out.println("R W");
        }else
            System.out.println("Invalid init command. Please enter 'I B' or I W' to initialize");

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


    public boolean isValidPosition(int player, int row, int col){
        boolean check = false;
        board[row][col] = player;
        try {
            //check north
            if (board[row-1][col] == player * -1){
                System.out.println("Checking North");
                for (int i = 1; i < row; i++){
                    if (board[(row-1)-i][col] == player) {
                        check = true;
                        break;
                    } else if (board[(row-1)-i][col] == 0){
                        check = false;
                        break;
                    }else
                        check= true;
                }
            //check west
            }else if (board[row][col-1] == player * -1){
                System.out.println("Checking west");
                for (int i = 1; i < col; i++){
                    if (board[row][(col-1)-i] == player) {
                        check = true;
                        break;

                    }else if (board[row][(col-1)-i] == 0){
                        check = false;
                        break;
                    }else
                        check = true;
                }
            //check northwest
            }else if (board[row-1][col-1] == player * -1){
                System.out.println("Checking northwest");
                for (int i = 1; i < col; i++){
                    if (board[(row-1)-i][(col-1)-1] == player ) {
                        check = true;
                        break;

                    }else if (board[(row-1)-i][(col-1)-1] == 0){
                        check = false;
                        break;
                    }else
                        check = true;
                }
            //check south
            }else if (board[row+1][col] == player * -1) {
                System.out.println("Checking south");
                for (int i = 1; i < 8-row; i++) {
                    if (board[(row + 1) + i][col] == player) {
                        check = true;
                        break;

                    } else if (board[(row + 1) + i][col] == 0) {
                        check = false;
                        break;
                    }else
                        check = true;
                }
            //check south west
            }else if (board[row+1][col-1] == player * -1){
                System.out.println("Checking southwest");
                for (int i = 1; i < row; i++){
                    if (board[(row+1)+i][(col-1)-i] == player) {
                        check = true;
                        break;

                    }else if (board[(row+1)+i][(col-1)-i] == 0){
                        check = false;
                        break;
                    }else
                        check = true;
                }
             //check east
            }else if (board[row][col+1] == player * -1){
                System.out.println("checking east");
                System.out.println("found op" +board[row][col+1]);
                for (int i = 1; i < 8-col; i++){
                    System.out.println(i);
                    if (board[row][col+1]+i == player) {
                        check = true;
                        System.out.println(board[row][col+1]+i+" is "+ check);
                        break;

                    }else if (board[row][(col+1)+i] == 0){
                        check = false;
                        break;
                    }else
                        check = true;
                }
            //check south-east
            }else if (board[row+1][col+1] == player * -1){
                System.out.println("Checking southeast");
                for (int i = 1; i < row-8; i++){
                    if (board[(row+1)+i][(col+1)+i] == player) {
                        check = true;
                        break;

                    }else if (board[(row+1)+i][(col+1)+i] == 0){
                        check = false;
                        break;
                    }else
                        check = true;
                }
            //check north-east
            }else if (board[row-1][col+1] == player * -1){
                System.out.println("checking north east");
                for (int i = 1; i < 8-col; i++){
                    if (board[(row-1)-i][(col+1)+i] == player) {
                        check = true;
                        break;

                    }else if (board[(row-1)-i][(col+1)+i] == 0){
                        check = false;
                        break;
                    }else
                        check = true;
                }
            }

        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("checked on out of bound space");
        }

        return check;
    }

    public boolean isGameOver(){
        if (gameOver){
            return true;
        }else
            return false;
    }
}
