import java.util.*;

/**
 * Created by bHodges on 4/18/16.
 */
public class Board {


    final int BORDER = -2;
    final int EMPTY = 0;
    final int NORTH = -10;
    final int NORTHEAST = -9;
    final int EAST = 1;
    final int SOUTHEAST = 11;
    final int SOUTH = 10;
    final int SOUTHWEST = 9;
    final int WEST = -1;
    final int NORTHWEST = -11;
    final String[] CORNER_LIST = {"A1", "A8", "H1", "H8"};
    final Set<String> CORNERS = new HashSet<String>(Arrays.asList(CORNER_LIST));
    final String[] ASPACES = {"D4", "E4", "D5", "E5", "C3", "C4", "C5", "C6", "D6", "E6", "F6", "F5", "F4", "F3", "E3", "D3", "C1",
            "F1", "H3", "H6", "F8", "C8", "A6", "A3"};
    final Set<String> AMOVES = new HashSet<String>(Arrays.asList(ASPACES));
    final String[] BSPACES = {"D1", "E1", "H4", "H5", "E8", "D8", "A5", "A4"};
    final Set<String> BMOVES = new HashSet<String>(Arrays.asList(BSPACES));
    final String[] CSPACES = {"B1", "G1", "H2", "H7", "G8", "B8", "A7", "A2"};
    final Set<String> CMOVES = new HashSet<String>(Arrays.asList(CSPACES));
    char myColor;
    char oppColor;
    int[] board = new int[100];
    public boolean gameOver;
    Board passedBoard;

    public Board(String initColor) {
        if (initColor.equals("IB")) {
            myColor = 'B';
            oppColor = 'W';
        } else {
            myColor = 'W';
            oppColor = 'B';
        }
        initBoard(initColor);
        gameOver = false;
    }

    public Board(Board oldBoard) {
        int[] pBoard = oldBoard.board;
        for (int i = 0; i < pBoard.length; i++) {
            board[i] = pBoard[i];
        }

    }

    //initializes new board
    private void initBoard(String initColor) {
        int black = 0, white = 0;

        if (initColor.equals("IB")) {
            black = 1;
            white = -1;
            System.out.println("R B");
        } else if (initColor.equals("IW")) {
            black = -1;
            white = 1;
            System.out.println("R W");
        } else
            System.out.println("C Invalid init command. Please enter 'I B' or I W' to initialize");

        for (int i = 0; i < board.length; i++) {

            if (i < 10) {
                board[i] = BORDER;
            } else if ((i % 10 == 0) || ((i + 1) % 10 == 0)) {
                board[i] = BORDER;
            } else if (i > 89) {
                board[i] = BORDER;
            } else if (i == 44 || i == 55) {
                board[i] = white;
            } else if (i == 45 || i == 54) {
                board[i] = black;
            } else
                board[i] = EMPTY;
        }
    }

    //Formats and prints board to console
    public void printBoard() {
        int count = 0;
        System.out.println("C");
        System.out.println("C         A  B  C  D  E  F  G  H");
        for (int i = 0; i < board.length; i++) {
            if (count == 10) {
                System.out.println();
                count = 0;
            }
            if (i % 10 == 0) {

                if ((i / 10 > 0) && (i / 10 < 9)) {
                    System.out.print("C  " + i / 10 + "  ");
                } else
                    System.out.print("C     ");

            }
            if (board[i] > -1) {
                if (board[i] == 1) {
                    System.out.print(" " + myColor + " ");
                } else
                    System.out.print(" " + board[i] + " ");
            } else {
                if (board[i] == -1) {
                    System.out.print(" " + oppColor + " ");
                } else
                    System.out.print("" + board[i] + " ");
            }
            count++;
        }
        System.out.println();
        System.out.println("C");
    }

    //generates all possible moves for player passed, gives them a rating, and sorts them
    public ArrayList<Move> generateMoves(Player player) {
        int playerNumber = player.getPlayerNumber();
        ArrayList<Move> moveList = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            if (isValidMove(playerNumber, i)) {
                Move move = new Move(i);
                if (CORNERS.contains(move.getMove())) {
                    move.setValue(500);
                } else if (AMOVES.contains(move.getMove())) {
                    move.setValue(250);
                } else if (BMOVES.contains(move.getMove())) {
                    move.setValue(100);
                } else if (CMOVES.contains(move.getMove())) {
                    move.setValue(50);
                } else
                    move.setValue(-5);

                moveList.add(move);
            }
        }

        //in case of no moves, add pass move
        if (moveList.size() > 0) {
            Collections.sort(moveList, new MoveComparator());
            Collections.reverse(moveList);
        } else {
            String pass = "" + player.getPlayerColor();
            Move move = new Move(pass);
            moveList.add(move);
        }

        player.assignPossibleMoves(moveList);
        return moveList;
    }


    //helper method to check in a direction passed for player piece and empty space
    private boolean directionCheck(int player, int space, int direction) {
        boolean check = false;
        int spaceToCheck = space + direction;
        if (board[spaceToCheck] == player * -1) {
            while (board[spaceToCheck] != BORDER) {
                spaceToCheck += direction;
                if (board[spaceToCheck] == player) {
                    check = true;
                    break;
                } else if (board[spaceToCheck] == EMPTY) {
                    check = false;
                    break;
                }
            }
        } else
            check = false;

        return check;
    }

    //checks is space is valid for player move
    private boolean isValidMove(int player, int space) {
        try {
            if (board[space] != 0) {
                return false;
            } else if (directionCheck(player, space, NORTH)) {
                return true;
            } else if (directionCheck(player, space, NORTHEAST)) {
                return true;
            } else if (directionCheck(player, space, EAST)) {
                return true;
            } else if (directionCheck(player, space, SOUTHEAST)) {
                return true;
            } else if (directionCheck(player, space, SOUTH)) {
                return true;
            } else if (directionCheck(player, space, SOUTHWEST)) {
                return true;
            } else if (directionCheck(player, space, WEST)) {
                return true;
            } else if (directionCheck(player, space, NORTHWEST)) {
                return true;
            } else
                return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;

        }
    }

    //flip opponent pieces in all directions
    public void flipEm(Player player, int position) {

        int playerNumber = player.getPlayerNumber();
        int oppNumber = playerNumber * -1;

        if (flipCheck(position, NORTH, playerNumber)) {
            int startPosition = position + NORTH;
            while (board[startPosition] == oppNumber) {
                board[startPosition] = playerNumber;
                startPosition += NORTH;
            }
        }

        if (flipCheck(position, NORTHEAST, playerNumber)) {
            int startPosition = position + NORTHEAST;
            while (board[startPosition] == oppNumber) {
                board[startPosition] = playerNumber;
                startPosition += NORTHEAST;
            }
        }

        if (flipCheck(position, EAST, playerNumber)) {
            int startPosition = position + EAST;
            while (board[startPosition] == oppNumber) {
                board[startPosition] = playerNumber;
                startPosition += EAST;
            }
        }

        if (flipCheck(position, SOUTHEAST, playerNumber)) {
            int startPosition = position + SOUTHEAST;
            while (board[startPosition] == oppNumber) {
                board[startPosition] = playerNumber;
                startPosition += SOUTHEAST;
            }
        }

        if (flipCheck(position, SOUTH, playerNumber)) {
            int startPosition = position + SOUTH;
            while (board[startPosition] == oppNumber) {
                board[startPosition] = playerNumber;
                startPosition += SOUTH;
            }
        }

        if (flipCheck(position, SOUTHWEST, playerNumber)) {
            int startPosition = position + SOUTHWEST;
            while (board[startPosition] == oppNumber) {
                board[startPosition] = playerNumber;
                startPosition += SOUTHWEST;
            }
        }

        if (flipCheck(position, WEST, playerNumber)) {
            int startPosition = position + WEST;
            while (board[startPosition] == oppNumber) {
                board[startPosition] = playerNumber;
                startPosition += WEST;
            }
        }

        if (flipCheck(position, NORTHWEST, playerNumber)) {
            int startPosition = position + NORTHWEST;
            while (board[startPosition] == oppNumber) {
                board[startPosition] = playerNumber;
                startPosition += NORTHWEST;
            }
        }
    }

    //helper method for flip to check direction
    public boolean flipCheck(int space, int direction, int playerColor) {
        int opponent = playerColor * -1;
        try {
            int checkSpace = space + direction;
            if (board[checkSpace] == opponent) {
                while (board[checkSpace] != BORDER && board[checkSpace] != EMPTY) {
                    if (board[checkSpace] == playerColor) {
                        return true;
                    } else {
                        checkSpace += direction;
                    }
                }
                return false;
            } else
                return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

    }


    //applies player move to board
    public void applyMove(Player player, String move) {
        if (move.length() == 2) {
            int playerNum = player.getPlayerNumber();
            int col = ((int) move.charAt(0)) - 64;
            int row = ((int) move.charAt(1)) - 48;
            int boardPosition = (row * 10) + col;
            if (isValidMove(playerNum, boardPosition)) {
                board[boardPosition] = playerNum;
                flipEm(player, boardPosition);
            } else {
                System.out.println("C This (" + move + ") is not a valid move, you loose.");
                gameOver = true;
            }
        } else if (move.length() > 2) {
            System.out.println("C Invalid move. Game over...");
            gameOver = true;
        }
    }

    //evaluates board for each player
    public void evaluateBoard(Player me, Player opp) {
        int myNumber = me.getPlayerNumber();
        int oppNumber = opp.getPlayerNumber();
        int myScore = 0, oppScore = 0;

        for (int i = 0; i < board.length; i++) {
            if (board[i] == myNumber) {
                myScore += rateSpace(i);

            } else if (board[i] == oppNumber) {
                oppScore += rateSpace(i);
            }
        }

        me.setCurrentBoardScore(myScore);
        opp.setCurrentBoardScore(oppScore);

        System.out.println("C " + me.getPlayerColor() + "'s current board score is: " + myScore);
        System.out.println("C " + opp.getPlayerColor() + "'s current board score is: " + oppScore);
    }

    //method which evaluates all spaces on board for specified player
    public int evaluateBoard(Player player) {
        int score = 0;

        for (int i = 0; i < board.length; i++) {
            if (board[i] == player.getPlayerNumber()) {
                score += rateSpace(i);
            }
        }
        return score;
    }


    //helper method to rate a space passed based on Othello space ratings
    private int rateSpace(int space) {
        int row = 0;
        char col;
        String move = null;
        int score = 0;

        col = (char) ((space % 10) + 64);
        row = space / 10;
        move = "" + col + "" + row;

        if (CMOVES.contains(move)) {
            score += 500;
        } else if (AMOVES.contains(move)) {
            score += 250;
        } else if (BMOVES.contains(move)) {
            score += 100;
        } else if (CMOVES.contains(move)) {
            score += 50;
        } else
            score += -5;


        return score;
    }

    //checks for game over
    public boolean isGameOver() {
        boolean iHasMove = false;
        boolean oppHasMove = false;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == EMPTY) {
                if (isValidMove(1, i)) {
                    iHasMove = true;
                }
                if (isValidMove(-1, i)) {
                    oppHasMove = true;
                }
            }
        }

        if (!iHasMove && !oppHasMove) {
            return true;
        }

        return false;
    }

    //counts number of pieces on board for player passed
    public int countPieces(Player player) {
        int playerNumber = player.getPlayerNumber();
        int count = 0;
        for (int space : board) {
            if (space == playerNumber) {
                count++;
            }
        }
        return count;
    }

    //checks to see who won the game
    public void whoWon(Player me, Player opp) {
        int myPieces = countPieces(me);
        int oppPieces = countPieces(opp);
        if (myPieces > oppPieces) {
            System.out.println("C " + me.getPlayerColor() + " has won the game with: " + myPieces);
        } else
            System.out.println("C " + opp.getPlayerColor() + " has won the game with: " + oppPieces);
    }

    //alpha-beta search
    public Move alphaBeta(Board currentBoard, int ply, Player player, double alpha, double beta, int maxDepth) {
            /*
                Algorithm provided by: Dr. Cameron
                Implemented by: Brett Hodges
            */
        if (ply >= maxDepth) {
            Move returnMove = new Move();
            returnMove.value = currentBoard.evaluateBoard(player);
            return returnMove;
        } else {
            ArrayList<Move> moves = currentBoard.generateMoves(player);
            Move bestMove = moves.get(0);
            for (Move move : moves) {
                Board newBoard = new Board(currentBoard);
                newBoard.applyMove(player, move.move);

                //Recursively Call alpha-beta
                Move tempMove = alphaBeta(newBoard, ply + 1, player.getOpponant(), beta * -1, alpha * -1, maxDepth);
                move.value = -tempMove.value;
                if (move.value > alpha) {
                    bestMove = move;
                    alpha = move.value;
                    if (alpha > beta)
                        return bestMove;
                }
            }
            return bestMove;
        }
    }
}

//Custom sort comparator class for sorting Move list
class MoveComparator implements Comparator<Move> {
    @Override
    public int compare(Move move1, Move move2) {
        return Integer.compare(move1.getValue(), move2.getValue());
    }
}
