package Model.Pieces;

import Model.ChessPiece;
import Model.Position;

public class Rook extends ChessPiece {

    private final int value = 5;

    public Rook(String name, Position position, String colour) {
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