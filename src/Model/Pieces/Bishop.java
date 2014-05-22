package Model.Pieces;

import Model.ChessPiece;
import Model.Position;

public class Bishop extends ChessPiece {

    private final int value = 3;

    public Bishop(String name, Position position, String colour) {
        super(name, position, colour);
    }

    @Override
    public Bishop clone(){
        Bishop copy = new Bishop(getName(), new Position(this.getPosition().getRow(), this.getPosition().getColumn()), this.getColour());
        copy.setImage(getImage());
        return copy;
    }

    @Override
    public int getValue() {
        return value;
    }
}