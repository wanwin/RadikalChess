package Model.Pieces;

import Model.ChessPiece;
import Model.Position;

public class Pawn extends ChessPiece {

    private final int value = 1;

    public Pawn(String name, Position position, String colour) {
        super(name, position, colour);
    }

    @Override
    public Pawn clone(){
        Pawn copy = new Pawn(getName(), new Position(this.getPosition().getRow(), this.getPosition().getColumn()), this.getColour());
        copy.setImage(getImage());
        return copy;
    }

    @Override
    public int getValue() {
        return value;
    }
}