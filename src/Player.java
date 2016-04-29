import java.util.ArrayList;

/**
 * Created by bHodges on 4/18/16.
 */
public class Player {
    char color;
    int playerNumber;
    ArrayList<Move> possibleMoves;
    Move move;
    double totalTimePlayed;
    double startTime;
    double endTime;
    final double TIMELIMIT = 6 * Math.pow(10, 11);
    boolean hasTimeLeft;
    int currentBoardScore;
    Player opponant;


    public Player(char initColor, int playerNumber) {
        color = initColor;
        this.playerNumber = playerNumber;
        totalTimePlayed = 0.0;
        startTime = 0.0;
        endTime = 0.0;
        hasTimeLeft = true;
        currentBoardScore = 0;
        opponant = null;
    }

    public void assignPossibleMoves(ArrayList<Move> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    public ArrayList<Move> getPossibleMoves() {
        return possibleMoves;
    }

    public Move pickMove() {
        return possibleMoves.get(0);
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public char getPlayerColor() {
        return color;
    }

    public void startTurn() {
        startTime = System.nanoTime();
    }

    public void endTurn() {
        endTime = System.nanoTime();
        totalTimePlayed += endTime - startTime;
        checkTime();
    }

    public void checkTime() {
        if (totalTimePlayed > TIMELIMIT) {
            hasTimeLeft = false;
            System.out.println("C Sorry, you have ran out of time and must forfeit");
        }
    }

    public int getCurrentBoardScore() {
        return currentBoardScore;
    }

    public void setCurrentBoardScore(int currentBoardScore) {
        this.currentBoardScore = currentBoardScore;
    }

    public Player getOpponant() {
        return opponant;
    }

    public void setOpponant(Player opponant) {
        this.opponant = opponant;
    }

}
