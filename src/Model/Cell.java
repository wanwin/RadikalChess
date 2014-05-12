package Model;

public class Cell {

    private ChessPiece chessPiece;
    private Position position;

    public Cell(ChessPiece chessPiece, Position position) {
        this.chessPiece = chessPiece;
        this.position = position;
    }

    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}