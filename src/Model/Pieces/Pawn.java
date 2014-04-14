package Model.Pieces;

import Model.ChessPiece;
import Model.Image;
import Model.Position;

public class Pawn extends ChessPiece {

    public Pawn(String name, Position position) {
        super(name, position);
    }

    @Override
    public Image loadImage() {
        return null;
    }
    
}
