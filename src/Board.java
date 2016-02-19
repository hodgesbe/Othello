import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bHodges on 2/10/16.
 */

//Board Class
public class Board {

    final int BORDER = -2;
    final int EMPTY = 0;
    final int ME = 1;
    final int OPPONENT = -1;
    char myColor;
    char oppColor;
    int[] board = new int[100];
    boolean gameOver = false;
    boolean pass = false;

    public Board(String initColor) {
        if (initColor.equals("IB")){
            myColor = 'B';
            oppColor = 'W';
        }else {
            myColor = 'W';
            oppColor = 'B';
        }
        initBoard(initColor);
    }

    private void initBoard(String initColor){
        int black = 0, white = 0;

        if (initColor.equals("IB")){
            black = 1;
            white = -1;
            System.out.println("R B");
        }else if(initColor.equals("IW")){
            black = -1;
            white = 1;
            System.out.println("R W");
        }else
            System.out.println("Invalid init command. Please enter 'I B' or I W' to initialize");

        for (int i = 0; i<board.length; i++){

            if(i<10){
                board[i] = BORDER;
            }else if ((i % 10 == 0) || ((i+1) % 10 ==0)){
                board[i] = BORDER;
            }else if (i > 89){
                board[i] = BORDER;
            }else if(i == 44 || i == 55){
                board[i] = white;
            }else if(i == 45 || i == 54){
                board[i] = black;
            }else
                board[i] = EMPTY;
        }
    }

    public void printBoard(){
        int count = 0;
        System.out.println("       A  B  C  D  E  F  G  H");
        for (int i = 0; i<board.length; i++){
            if (count == 10){
                System.out.println();
                count = 0;
            }
            if (i%10 == 0){

                if ((i/10 > 0) && (i/10 < 9)){
                    System.out.print(i/10+"  ");
                }else
                    System.out.print("   ");
            }
            if (board[i] > -1){
                    System.out.print(" "+board[i]+" ");
            }else
                    System.out.print(board[i]+" ");

            count++;
        }
        System.out.println();
    }


    public ArrayList generateMoves(int player){

        ArrayList<String> moveList = new ArrayList<>();
        int row = 0;
        char col;
                ;
        for (int i = 0; i < board.length; i++){

            if (isValidMove(player, i)){
                col = (char) ((i%10)+64);
                row = i / 10;
                moveList.add(""+col+""+row);
                System.out.println("found move: "+col+" "+row);
            }

        }

        return moveList;
    }

    public void applyMove(String move){
        try {


            int player;

            if (move.charAt(0) == myColor) {
                player = ME;
            } else
                player = OPPONENT;

            if (move.length() == 1) {
                pass = true;
                System.out.println(move);
            } else {

                int col = ((int) move.charAt(1)) - 64;
                int row = ((int) move.charAt(2)) - 48;
                int boardPosition = (row * 10) + col;
                if (isValidMove(player, boardPosition)) {
                    board[boardPosition] = player;
                    System.out.println(move.charAt(0) + " " + move.charAt(1) + " " + move.charAt(2));
                } else {
                    System.out.println("C This is not a valid move, please enter another...");
                    applyMove(Game.readInput());
                }
            }
        } catch (StringIndexOutOfBoundsException e){
            System.out.println("C Please enter a valid command.");
            applyMove(Game.readInput());
        }
    }

    //method for flipping captured pieces
    private void flip(){

    }

    public boolean gameOver(){
        boolean meCheck = false;
        boolean oppCheck = false;

        //Check if computer has valid move
        for (int i = 11; i < board.length-11; i++){
            meCheck = isValidMove(ME, i);
        }

        //Check if Opponent has valid move
        for (int i = 11; i < board.length-11; i++){
            oppCheck = isValidMove(OPPONENT, i);
        }

        //if neither player has valid move, game over.
        if (!meCheck && !oppCheck){
            return true;
        }else
            return false;
    }

    private boolean isValidMove(int player, int space){
        boolean isValid = false;
        int tempSpace;
        try {
            // is zero
            if (board[space] == EMPTY) {
                //check north -10
                if (board[space-10] == (player * -1)){
                    tempSpace = space-20;
                    while (board[tempSpace] != BORDER){
                        if (board[tempSpace] == player){
                            isValid = true;
                            break;
                        }else if (board[tempSpace] == 0){
                            isValid = false;
                            break;
                        }
                        tempSpace-=10;
                    }
                }//end north

                //check northeast -9
                else if (board[space-9] == (player * -1)){
                    tempSpace = space-18;
                    while (tempSpace != BORDER){
                        if (board[tempSpace] == player){
                            isValid = true;
                            break;
                        }else if (board[tempSpace] == 0){
                            isValid = false;
                            break;
                        }
                        tempSpace -=9;
                    }

                }//end northeast

                //check east +1
                else if (board[space+1] == (player * -1)){
                    tempSpace = space+2;
                    while (board[tempSpace] != BORDER){
                        if (board[tempSpace] == player){
                            isValid = true;
                            break;
                        }else if (board[tempSpace] == 0){
                            isValid = false;
                            break;
                        }
                        tempSpace++;
                    }
                    
                }//end east

                //check southeast +11
                else if (board[space+11] == (player * -1)){
                    tempSpace = space+22;
                    while (board[tempSpace] != BORDER){
                        if (board[tempSpace] == player){
                            isValid = true;
                            break;
                        }else if (board[tempSpace] == 0){
                            isValid = false;
                            break;
                        }
                        tempSpace+=11;
                    }
                }//end southeast

                //check south +10
                else if (board[space+10] == (player * -1)){
                    tempSpace = space+20;
                    while (board[tempSpace] != BORDER){
                        if (board[tempSpace] == player){
                            isValid = true;
                            break;
                        }else if (board[tempSpace] == 0){
                            isValid = false;
                            break;
                        }
                        tempSpace+=10;
                    }
                }//end south

                //check southwest +9
                else if (board[space+9] == (player * -1)){
                    tempSpace = space+18;
                    while (board[tempSpace] != BORDER){
                        if (board[tempSpace] == player){
                            isValid = true;
                            break;
                        }else if (board[tempSpace] == 0){
                            isValid = false;
                            break;
                        }
                        tempSpace+=9;
                    }
                }//end southwest
                //check west -1
                else if (board[space-1] == (player * -1)){
                    tempSpace = space-2;
                    while (board[tempSpace] != BORDER){
                        if (board[tempSpace] == player){
                            isValid = true;
                            break;
                        }else if (board[tempSpace] == 0){
                            isValid = false;
                            break;
                        }
                        tempSpace-=1;
                    }
                }//end west
                //check northwest -11
                else if (board[space-11] == (player * -1)){
                    tempSpace = space-22;
                    while (board[tempSpace] != BORDER){
                        if (board[tempSpace] == player){
                            isValid = true;
                            break;
                        }else if (board[tempSpace] == 0){
                            isValid = false;
                            break;
                        }
                        tempSpace-=11;
                    }
                }//end northwest
            }

        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Checked out of bounds");
        }

        return isValid;
    }

}


