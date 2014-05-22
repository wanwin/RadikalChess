package Model.Pieces;

import Model.ChessPiece;
import Model.Position;

public class King extends ChessPiece {

    private final double value = 100;

    public King(String name, Position position, String colour) {
        super(name, position, colour);
    }

    @Override
    public King clone(){
        King copy = new King(getName(), new Position(this.getPosition().getRow(), this.getPosition().getColumn()), this.getColour());
        copy.setImage(getImage());
        return copy;
    }

    @Override
    public int getValue() {
        return (int) value;
    }
}