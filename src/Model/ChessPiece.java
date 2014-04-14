package Model;

public abstract class ChessPiece {
    private final String name;
    private final Position position;

    public ChessPiece(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }
    
    public abstract Image loadImage();
}