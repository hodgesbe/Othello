import java.util.ArrayList;

/**
 * Created by bHodges on 4/18/16.
 */
public class Move {

    int value;
    int row;
    char column;
    int representation;
    String move;

    public Move() {
        value = 0;
        row = 0;
        column = (char) 0;
        int representation = 0;
        move = null;
    }

    public Move(String move) {
        if (move.length() > 1) {
            move = move.toUpperCase();
            int moveCol = ((int) move.charAt(0)) - 64;
            int moveRow = ((int) move.charAt(1)) - 48;
            representation = (moveRow * 10) + moveCol;
            column = move.charAt(0);
            row = (int) move.charAt(1);
            this.move = move;
        } else
            this.move = move;
    }

    public Move(int moveRepresentation) {
        representation = moveRepresentation;
        column = (char) ((moveRepresentation % 10) + 64);
        row = moveRepresentation / 10;
        move = "" + column + row;
    }

    public char getColumn() {
        return column;
    }

    public void setColumn(char column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRepresentation() {
        return representation;
    }

    public void setRepresentation(int representation) {
        this.representation = representation;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }
}
