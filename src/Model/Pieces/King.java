package Model.Pieces;

import Model.ChessPiece;
import Model.Position;

public class King extends ChessPiece {

    private final double value = Double.POSITIVE_INFINITY;

    public King(String name, Position position, String colour) {
        super(name, position, colour);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return null;
    }

    @Override
    public int getValue() {
        return (int) value;
    }
}