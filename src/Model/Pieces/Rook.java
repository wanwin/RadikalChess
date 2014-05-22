package Model.Pieces;

import Model.ChessPiece;
import Model.Position;

public class Rook extends ChessPiece {

    private final int value = 5;

    public Rook(String name, Position position, String colour) {
        super(name, position, colour);
    }

    @Override
    public Rook clone(){
        Rook copy = new Rook(getName(), new Position(this.getPosition().getRow(), this.getPosition().getColumn()), this.getColour());
        copy.setImage(getImage());
        return copy;
    }

    @Override
    public int getValue() {
        return value;
    }
}