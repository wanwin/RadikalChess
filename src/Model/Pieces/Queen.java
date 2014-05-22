package Model.Pieces;

import Model.ChessPiece;
import Model.Position;

public class Queen extends ChessPiece {

    private final int value = 9;

    public Queen(String name, Position position, String colour) {
        super(name, position, colour);
    }

    @Override
    public Queen clone(){
        Queen copy = new Queen(getName(), new Position(this.getPosition().getRow(), this.getPosition().getColumn()), this.getColour());
        copy.setImage(getImage());
        return copy;
    }

    @Override
    public int getValue() {
        return value;
    }
}