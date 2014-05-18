package Model.Pieces;

import Model.ChessPiece;
import Model.Position;

public class Queen extends ChessPiece {

    private final int value = 9;

    public Queen(String name, Position position, String colour) {
        super(name, position, colour);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Queen copy = new Queen(getName(), this.getPosition(), this.getColour());
        copy.setImage(getImage());
        return copy;
    }

    @Override
    public int getValue() {
        return value;
    }
}