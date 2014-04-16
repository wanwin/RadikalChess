package Model.aima;

import Model.aima.XYLocation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A state of the Tic-tac-toe game is characterized by a board containing
 * symbols X and O, the next player to move, and an utility information.
 * 
 * @author Ruediger Lunde
 * 
 */
public class RadikalChessState implements Cloneable {
    
    public static final String O = "O";
    public static final String X = "X";
    public static final String EMPTY = "-";
    private String[] board; 
    private String playerToMove = X;
    private double utility = -1;

    public String getPlayerToMove() {
        return playerToMove;
    }
    
    public boolean isEmpty(int col, int row) {
        return board[getAbsPosition(col, row)] == EMPTY;
    }

    public String getValue(int col, int row) {
        return board[getAbsPosition(col, row)];
    }

    public double getUtility() {
        return utility;
    }

    public void mark(XYLocation action) {
        mark(action.getXCoOrdinate(), action.getYCoOrdinate());
    }

    public void mark(int col, int row) {
        if (utility == -1 && getValue(col, row) == EMPTY) {
            board[getAbsPosition(col, row)] = playerToMove;
            analyzeUtility();
            playerToMove = (playerToMove == X ? O : X);
        }
    }

    private void analyzeUtility() {
        if (lineThroughBoard()) {
            utility = (playerToMove == X ? 1 : 0);
        } else if (getNumberOfMarkedPositions() == 4*6) {
            utility = 0.5;
        }
    }

    public boolean lineThroughBoard() {
        return (isAnyRowComplete() || isAnyColumnComplete() || isAnyDiagonalComplete());
    }
    
    private boolean isAnyRowComplete() {
        for (int row = 0; row < 4; row++) {
            String val = getValue(0, row);
            if (val != EMPTY){
                for(int i=1;i<4;i++){
                    if(val!=getValue(i, row))
                        break;
                    else if(i == 4-1){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isAnyColumnComplete() {
        for (int col = 0; col < 6; col++) {
            String val = getValue(col, 0);
            if (val != EMPTY) {
                for(int i=1;i<6;i++){
                    if(val!=getValue(col, i))
                        break;
                    else if(i == 6-1){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isAnyDiagonalComplete() {
        boolean retVal = false;
        String val = getValue(0, 0);
        if (val != EMPTY) {
            for(int i=1;i<4;i++){
                if(val!=getValue(i, i))
                    break;
                else if(i == 4-1)
                    return true;
            }
        }
        val = getValue(0, 4-1);
        int fila=1;
        if (val != EMPTY) {
            for(int i = 4-1;i>=0;i--){
                if(val!=getValue(fila, i-1))
                    break;
                else if(i==0)
                    return true;
                fila++;
            }
        }
        return retVal;
    }

    public int getNumberOfMarkedPositions() {
        int retVal = 0;
        for (int col = 0; col < 6; col++) {
            for (int row = 0; row < 4; row++) {
                if (!(isEmpty(col, row))) {
                    retVal++;
                }
            }
        }
        return retVal;
    }

    public List<XYLocation> getUnMarkedPositions() {
        List<XYLocation> result = new ArrayList<XYLocation>();
        for (int col = 0; col < 6; col++) {
            for (int row = 0; row < 4; row++) {
                if (isEmpty(col, row)) {
                    result.add(new XYLocation(col, row));
                }
            }
        }
        return result;
    }

    @Override
    public RadikalChessState clone() {
        RadikalChessState copy = null;
        try {
            copy = (RadikalChessState) super.clone();
            copy.board = Arrays.copyOf(board, board.length);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace(); // should never happen...
        }
        return copy;
    }

    @Override
    public boolean equals(Object anObj) {
        if (anObj != null && anObj.getClass() == getClass()) {
            RadikalChessState anotherState = (RadikalChessState) anObj;
            for (int i = 0; i < 4*6; i++) {
                if (board[i] != anotherState.board[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        // Need to ensure equal objects have equivalent hashcodes (Issue 77).
        return toString().hashCode();
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 6; col++) {
                strBuilder.append(getValue(col, row) + " ");
            }
            strBuilder.append("\n");
        }
        return strBuilder.toString();
    }

    //
    // PRIVATE METHODS
    //

    private int getAbsPosition(int col, int row) {
        return row * 4 + col;
    }
}
