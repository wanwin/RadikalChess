package Model.Pieces;

import Model.ChessPiece;
import Model.Position;

public class King extends ChessPiece {

    private final double value = 100;

    public King(String name, Position position, String colour) {
        super(name, position, colour);
    }

    @Override
    protected King clone() throws CloneNotSupportedException {
        King copy = new King(getName(), this.getPosition(), this.getColour());
        copy.setImage(getImage());
        return copy;
    }

    @Override
    public int getValue() {
        return (int) value;
    }
}