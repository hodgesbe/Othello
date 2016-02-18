import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bHodges on 2/10/16.
 */

//Board Class
public class Board {

    public static final int BORDER = -2;
    public static final int EMPTY = 0;
    int[] board = new int[100];
    boolean gameOver = false;

    public Board(String initColor) {
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
            }
        }
    }

    public void printBoard(){
        int count = 0;
        for (int i = 0; i<board.length; i++){
            if (count == 10){
                System.out.println();
                count = 0;
            }
            if (board[i]>-1){
                    System.out.print(" "+board[i]+" ");
            }else
                    System.out.print(board[i]+" ");

            count++;
        }
    }

    private boolean isValidMove(int player, int space){
        boolean isValid = false;
        int tempSpace;
        try {
            // is zero
            if (board[space] == 0) {
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

    public ArrayList generateMoves(int player){
        ArrayList<Integer> moveList = new ArrayList<Integer>();
        for (int i = 0; i < board.length; i++){
            if (isValidMove(player, i)){
                moveList.add(new Integer(i));
            }
        }
        return moveList;
    }
}


