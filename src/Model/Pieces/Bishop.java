package Model.Pieces;

import Model.ChessPiece;
import Model.Position;

public class Bishop extends ChessPiece {

    private final int value = 3;

    public Bishop(String name, Position position, String colour) {
        super(name, position, colour);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return null;
    }

    @Override
    public int getValue() {
        return value;
    }
}