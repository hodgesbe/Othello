import java.util.Scanner;

/**
 * Created by bHodges on 4/18/16.
 */
public class Game {

    String initColor;
    Board gameBoard;
    Player opp;
    Player me;
    Player currentPlayer;

    public Game() {
        initColor = readInput();

        if (initColor.equals("IB")) {
            me = new Player('B', 1);
            opp = new Player('W', -1);
            currentPlayer = me;
        } else {
            me = new Player('W', 1);
            opp = new Player('B', -1);
            currentPlayer = opp;
        }

        me.setOpponant(opp);
        opp.setOpponant(me);
        gameBoard = new Board(initColor);
        playGame();
    }

    //Method to start playing a game
    public void playGame() {
        String oppMove = null;
        String move = null;

        //while game is not over and both players still have time left
        while (me.hasTimeLeft && opp.hasTimeLeft && !gameBoard.isGameOver()) {

            //if it is my turn
            if (currentPlayer == me) {
                me.startTurn();
                gameBoard.generateMoves(me);
                Move myMove = gameBoard.alphaBeta(gameBoard, 0, me, Double.MIN_VALUE, Double.MAX_VALUE, 10);
                if (myMove.move.length() == 1) {
                    System.out.println(myMove.move);
                    me.endTurn();
                    currentPlayer = opp;
                } else {
                    gameBoard.applyMove(me, myMove.move);
                    System.out.println("" + me.getPlayerColor() + " " + myMove.move.toLowerCase().charAt(0) + " " + myMove.move.charAt(1));
                    gameBoard.printBoard();
                    me.endTurn();
                    currentPlayer = opp;
                }

                //else it is opp turn
            } else {
                opp.startTurn();
                oppMove = readInput();

                //checks for pass move
                if (oppMove.length() > 1) {
                    move = "" + oppMove.charAt(1) + oppMove.charAt(2);
                    gameBoard.applyMove(opp, move);
                    gameBoard.printBoard();
                    opp.endTurn();
                    currentPlayer = me;
                } else
                    opp.endTurn();
                currentPlayer = me;
            }
            //evaluate board after both moves have been made
            gameBoard.evaluateBoard(me, opp);
        }

        if (gameBoard.isGameOver()) {
            gameBoard.whoWon(me, opp);
        }
    }

    //Method to read input from opposing player
    public String readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.charAt(0) == 'C') {
            readInput();
        }
        return input.replaceAll("\\s+", "").toUpperCase();
    }

    public static void main(String[] args) {
        Game testGame = new Game();
        testGame.playGame();
    }


}
