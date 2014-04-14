package Model.Pieces;

import Model.ChessPiece;
import Model.Image;
import Model.Position;

public class Queen extends ChessPiece {

    public Queen(String name, Position position) {
        super(name, position);
    }

    @Override
    public Image loadImage() {
        return null;
    }
}
